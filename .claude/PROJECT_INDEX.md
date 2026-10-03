# Project Index: kitshn
## 1. Core Purpose
kitshn is an unofficial multiplatform client for the self-hostable Tandoor Recipes application, built with Compose and Material 3 Expressive. This particular repository is a fork of the upstream `kitshn-app/kitshn` project, used to apply specific adjustments. The application targets Android, iOS, and Desktop platforms, providing a modern, intuitive, and responsive user experience for managing recipes, meal plans, and shopping lists.

## 2. Architecture
The project is structured as a Kotlin Multiplatform (KMP) application using Compose Multiplatform for UI. It consists of several modules:
- `shared`: Contains common business logic, data models, and UI components reusable across all platforms.
- `androidApp`: Android-specific entry point and platform integrations.
- `iosApp`: iOS-specific entry point and platform integrations (SwiftUI and Compose interop).
- `desktopApp`: Desktop-specific entry point and platform integrations.
- `composeApp`: A module likely housing the primary Compose Multiplatform UI logic that bridges `shared` with platform-specific presentation.
- `web`: A separate web documentation site built with VitePress.
- `e2e`: Contains end-to-end testing infrastructure, including a `mtls-test-server` using Docker Compose, Nginx, and shell scripts for certificate setup and user seeding.
- `fastlane`: Automation for mobile app deployment, screenshots, and metadata management.
- `flatpak`: Configuration for Flatpak distribution on Linux.
- `gradle`: Gradle wrapper and centralized dependency versions (`libs.versions.toml`).
The repository maintains a fork strategy with `origin` pointing to `festion/kitshn` (our fork) and `upstream` to `kitshn-app/kitshn` (the original).

## 3. Key Files
- `README.md`: Primary project documentation, features, and installation instructions.
- `CLAUDE.md`: Fork-specific project instructions, remote setup, branch strategy, build notes, and details on inherited upstream configurations.
- `build.gradle.kts` (root), `androidApp/build.gradle.kts`, `desktopApp/build.gradle.kts`, `shared/build.gradle.kts`: Gradle build scripts defining project structure, dependencies, and build logic for respective modules.
- `gradle/libs.versions.toml`: Centralized declaration of dependency versions for Gradle.
- `kitshn.properties`: Configuration file containing upstream-specific settings like ACRA crash reporting endpoints and funding URLs.
- `.gitleaks.toml`, `.gitleaksignore`: Configuration for Gitleaks secret scanning, with repo-local exemptions.
- `.github/workflows/`: GitHub Actions CI/CD pipeline definitions for Android debug builds, desktop distributions, Flatpak, nightly builds, secret scanning, and web deployments.
- `fastlane/Fastfile`, `fastlane/actions/`: Fastlane configuration and custom actions for mobile release automation.
- `flatpak/app.kitshn.kitshn.yml`: Flatpak manifest for building and distributing the desktop application.
- `e2e/mtls-test-server/docker-compose.yml`, `e2e/mtls-test-server/setup-certs.sh`: Defines and configures the mTLS test server environment and its certificate setup.
- `web/`: Contains the source for the project's documentation website, including `index.md`, `download.md`, and feature documentation under `web/docs/`.

## 4. Dependencies
- **Build Tool:** Gradle, with Kotlin DSL.
- **Languages/Frameworks:** Kotlin, Compose Multiplatform, Swift (for iOS interop), JavaScript/TypeScript (for web docs/tools).
- **Backend:** Tandoor Recipes application (self-hostable).
- **CI/CD:** GitHub Actions.
- **Mobile Automation:** Fastlane (Ruby-based).
- **Web Documentation:** VitePress (Node.js/npm ecosystem).
- **Version Control:** Git, with specific remote configurations for `origin` (festion fork) and `upstream` (original kitshn-app).
- **Secrets Management:** Infisical (mentioned as source of truth for `DEBUG_KEYSTORE_B64`).
- **Crash Reporting:** ACRA (to `acra.kitshn.app`).
- **Translation:** Weblate.

## 5. Common Tasks
- **Build Android Debug APK:** Execute `./gradlew :androidApp:assembleDebug`. The output APK is found in `androidApp/build/outputs/apk/debug/`.
- **Sync with Upstream:** Run `git fetch upstream && git merge upstream/main` from a feature branch, then open a pull request to `main`.
- **Create Pull Request to Fork:** Use `gh pr create --repo festion/kitshn --base main` to ensure PRs target the correct repository and branch.
- **Push Changes:** Use the `git pushx` alias, which also regenerates the `.claude/PROJECT_INDEX.md` file.
- **Codebase Security Scan:** GitHub Actions workflow `.github/workflows/secret-scan.yml` automatically scans for secrets on pushes and PRs.
