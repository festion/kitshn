# kitshn (festion fork) — project instructions

Fork of [kitshn-app/kitshn](https://github.com/kitshn-app/kitshn), the Kotlin
Multiplatform (Compose) client for **Tandoor Recipes**. The operator runs it on a
Pixel phone against the homelab Tandoor instance. This fork exists so we can
make our own adjustments to the app.

- Tandoor instance facts, secrets and API quirks live in `~/workspace/tandoor/CLAUDE.md`.
  Read that before touching anything that talks to the server.
- Licence: **GPL-3.0** (inherited). Changes we distribute stay GPL.

## Remotes and branches

| remote     | URL                                        | role                     |
|------------|--------------------------------------------|--------------------------|
| `origin`   | `https://github.com/festion/kitshn.git`    | our fork — push here     |
| `upstream` | `https://github.com/kitshn-app/kitshn.git` | the real app — read only |

- `main` carries upstream plus our changes. Work on feature branches in
  `.worktrees/` and merge to `main` by PR on **festion/kitshn**.
- **`gh pr create` in a fork defaults to the UPSTREAM repo.** Always pass
  `--repo festion/kitshn --base main`, or the PR lands on kitshn-app.
- Sync upstream: `git fetch upstream && git merge upstream/main` on a branch,
  then PR it. Keep our diff in NEW files where possible so syncs stay clean.

## Building for the phone

- Module `androidApp`. Build types: `debug` (applicationId suffix `.debug`),
  `nightly` (`.nightly`), `release`.
- **Use `debug` or `nightly` for our builds.** The suffix lets them install
  BESIDE the Play Store kitshn. A `release` build reuses the Play Store
  applicationId, and Android refuses to install it over the store copy
  because the signing key differs.
- Debug APK: `./gradlew :androidApp:assembleDebug` → `androidApp/build/outputs/apk/debug/`.
- CT 128 has JDK 17 but **no Android SDK** (`ANDROID_HOME` unset) as of 2026-10-03,
  so builds run in CI: `.github/workflows/android-debug.yml`.
  - Every PR / push to `main` uploads `kitshn-debug-<sha>.apk` as a run artifact.
  - Push to `main` also replaces the **`debug-latest`** pre-release — open
    `https://github.com/festion/kitshn/releases/tag/debug-latest` on the phone;
    the direct file is `.../releases/download/debug-latest/kitshn-debug.apk`.
  - Signed with a FIXED debug key (repo secret `DEBUG_KEYSTORE_BASE64`, source of
    truth Infisical `KITSHN_DEBUG_KEYSTORE_B64`) so each build installs as an
    update over the last. Losing/replacing that key means uninstalling the
    `.debug` app once. The expected signer fingerprint is in the workflow header.

## Upstream config to know about (`kitshn.properties`)

- `acra.http.*` points at **upstream's** ACRA crash collector (`acra.kitshn.app`).
  **ACRA is disabled in this fork** (kitshn #4243): `AndroidApp.attachBaseContext`
  no longer calls `initKitshnAcra()`, and `CrashReporting.android.kt` returns a
  null handler, which hides the "send crash report" buttons as on desktop. Both
  edits carry a `festion fork:` comment — keep them when merging upstream. The
  values are still compiled into BuildConfig but nothing reads them. The
  login/password are upstream's, public by design, and exempted from gitleaks
  by fingerprint in `.gitleaksignore`.
- `funding.*`, `about.*`, `share.wrapper.url` also point at upstream services.

## Our changes to upstream code (keep them when merging upstream)

Each edit carries a `festion fork:` comment; `grep -rn "festion fork:" --include='*.kt' .` lists them.

- **ACRA disabled** — `AndroidApp.kt`, `CrashReporting.android.kt` (see above).
- **Ingredients above step text on phones** — `RecipeStepCard.kt`. When the
  card is too narrow for side by side, it stacks header → ingredients →
  instructions (upstream: header → instructions → ingredients). Side-by-side
  layout is unchanged. Applies wherever `RecipeStepCard` is used: the recipe
  view and the step list in the recipe editor (`creationandedit/StepsPage.kt`).
  Cook mode (`cook/page/RecipeStep.kt`) is separate and still stacks
  instructions first.

## Fleet conventions applied here

- `.gitleaks.toml` is the `operations/templates/gitleaks/` template, verbatim.
  Repo-local exemptions go in `.gitleaksignore` (fingerprints), not in the template.
- `.github/workflows/secret-scan.yml` gates the working tree on push to `main` and on PRs.
  Upstream's own workflows (`dist_*`, `nightly`, `web`) are inherited unchanged.
- `.gitattributes` sets `merge=union` on `.claude/PROJECT_INDEX.md`.
- Push with `git pushx` (regenerates `.claude/PROJECT_INDEX.md`).
- Work tracking: Vikunja project `kitshn`.
