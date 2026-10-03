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
- CT 128 has JDK 17 but **no Android SDK** (`ANDROID_HOME` unset) as of 2026-10-03.

## Upstream config to know about (`kitshn.properties`)

- `acra.http.*` sends crash reports to **upstream's** ACRA collector
  (`acra.kitshn.app`). Builds of this fork report there too until that is
  changed. The login/password in that file are upstream's, public by design;
  they are exempted from gitleaks by fingerprint in `.gitleaksignore`.
- `funding.*`, `about.*`, `share.wrapper.url` also point at upstream services.

## Fleet conventions applied here

- `.gitleaks.toml` is the `operations/templates/gitleaks/` template, verbatim.
  Repo-local exemptions go in `.gitleaksignore` (fingerprints), not in the template.
- `.github/workflows/secret-scan.yml` gates the working tree on push to `main` and on PRs.
  Upstream's own workflows (`dist_*`, `nightly`, `web`) are inherited unchanged.
- `.gitattributes` sets `merge=union` on `.claude/PROJECT_INDEX.md`.
- Push with `git pushx` (regenerates `.claude/PROJECT_INDEX.md`).
- Work tracking: Vikunja project `kitshn`.
