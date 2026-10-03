# Project Index: kitshn
## 1. Core Purpose
The `kitshn` project is a Kotlin Multiplatform (Compose) client for the self-hostable Tandoor Recipes application. This particular repository is a fork, maintained by 'festion', with the purpose of making custom adjustments to the upstream `kitshn-app/kitshn` project. It aims to provide a modern, intuitive, and responsive user experience across Android, iOS, and Desktop platforms, built with Compose and Material 3 Expressive.

## 2. Architecture
The codebase leverages Kotlin Multiplatform (KMP) to share code across different platforms, with specific modules for each target:
-   **`shared`**: Contains common Kotlin logic, UI components (Compose Multiplatform), and data models accessible by all platforms.
-   **`androidApp`**: Android-specific implementation, including `AndroidManifest.xml`, resources, and Gradle configuration.
-   **`iosApp`**: iOS-specific implementation, including Swift code, project files, and assets.
-   **`desktopApp`**: Desktop-specific implementation, including Gradle configuration, desktop icons, and packaging details.
-   **`composeApp`**: Likely contains shared Compose UI logic that is platform-agnostic but built with Compose Multiplatform.
-   **`web`**: A separate web project, potentially for documentation or a landing page, built with Node.js/npm.
-   **`e2e/mtls-test-server`**: Contains configurations for an end-to-end test server using Docker, Nginx, and scripts for certificate setup and user seeding.
-   **`fastlane`**: Mobile automation and release tooling for Android and iOS.
-   **`.github/workflows`**: Houses GitHub Actions for CI/CD, including Android debug builds, desktop/flatpak distribution, nightly builds, and secret scanning.

## 3. Key Files
-   **`CLAUDE.md`**: Project-specific instructions for this fork, including build notes, remote configurations, and fleet conventions.
-   **`README.md`**: General project overview, installation instructions, features list, and localization details.
-   **`build.gradle.kts` (root, `androidApp`, `desktopApp`, `shared`)**: Gradle build scripts for various modules, defining dependencies and build logic.
-   **`gradle/libs.versions.toml`**: Centralized dependency version catalog for Gradle.
-   **`.github/workflows/*.yml`**: GitHub Actions workflow definitions for continuous integration and deployment (`android-debug.yml`, `dist_desktop.yml`, `nightly.yml`, `secret-scan.yml`, `web.yml`).
-   **`fastlane/Fastfile`**: Fastlane configuration for mobile release automation.
-   **`flatpak/app.kitshn.kitshn.yml`**: Flatpak manifest for building the Linux desktop application.
-   **`e2e/mtls-test-server/docker-compose.yml`**: Docker Compose definition for the E2E test server.
-   **`kitshn.properties`**: Configuration properties for upstream services (ACRA, funding, share wrapper URL).
-   **`.gitleaks.toml`, `.gitleaksignore`**: Configuration for gitleaks secret scanning and local exemptions.
-   **`web/package.json`**: Dependencies and scripts for the `web` project.
-   **`.claude/PROJECT_INDEX.md`**: This project index file, managed by `git pushx`.

## 4. Dependencies
-   **Build System**: Gradle
-   **Languages/Frameworks**: Kotlin Multiplatform, Jetpack Compose / Compose Multiplatform, Material 3 Expressive.
-   **Mobile Automation**: Fastlane (for Android and iOS).
-   **Web**: Node.js/npm, VitePress (implied by `.vitepress` directory).
-   **E2E Testing**: Docker, Nginx.
-   **Version Control**: Git.
-   **CI/CD**: GitHub Actions.

## 5. Common Tasks
-   **Build Android Debug APK**: Execute `./gradlew :androidApp:assembleDebug` to generate a debug APK in `androidApp/build/outputs/apk/debug/`.
-   **Sync with Upstream**: Run `git fetch upstream && git merge upstream/main` to pull changes from the original `kitshn-app/kitshn` repository.
-   **Create Pull Request**: When contributing to the `festion/kitshn` fork, use `gh pr create --repo festion/kitshn --base main`.
-   **CI/CD**: GitHub Actions automatically build Android debug APKs on PRs and pushes to `main`, and manage other distributions and scans.
-   **Secret Scanning**: Managed by `.github/workflows/secret-scan.yml` using `gitleaks`.
-   **Update Project Index**: The `.claude/PROJECT_INDEX.md` file is regenerated via `git pushx`.
