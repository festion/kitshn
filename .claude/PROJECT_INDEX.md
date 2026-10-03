# Project Index: kitshn
## 1. Core Purpose
`kitshn` is an unofficial multiplatform client for the self-hostable Tandoor Recipes application, built with Compose and Material 3 Expressive. This specific fork (`festion/kitshn`) introduces custom adjustments while maintaining compatibility with the upstream project. It aims to provide a modern, intuitive, and responsive user experience across Android, iOS, and Desktop platforms for managing recipes, meal plans, and shopping lists.

## 2. Architecture
The project employs a Kotlin Multiplatform architecture, separating platform-specific code from shared logic and UI.
-   **`shared` module**: Contains common Kotlin code, business logic, and Compose Multiplatform UI components used across all platforms.
-   **`androidApp`**: Android-specific implementation, building debug, nightly, and release APKs.
-   **`iosApp`**: iOS-specific implementation for native integration.
-   **`desktopApp`**: Desktop-specific implementation, including packaging for Linux (AppImage) and potentially Windows/macOS.
-   **`composeApp`**: Likely contains the root Compose Multiplatform application entry point and shared UI resources.
-   **`web`**: Houses the project's public website and documentation, built with VitePress.
-   **`fastlane`**: Manages mobile app release automation, including versioning, changelogs, screenshots, and release notes.
-   **`.github/workflows`**: Implements CI/CD pipelines for Android builds, desktop distribution, Flatpak, nightly builds, and secret scanning.

## 3. Key Files
-   **`CLAUDE.md`**: Internal project instructions for this fork, detailing build processes, Git remote management, and specific configuration notes related to `kitshn.properties`.
-   **`.claude/PROJECT_INDEX.md`**: This generated project index.
-   **`README.md`**: Public-facing documentation, installation instructions, features, and screenshots.
-   **`build.gradle.kts` (root)**: Top-level Gradle build configuration.
-   **`gradle/libs.versions.toml`**: Centralized dependency versions and plugin management for Gradle.
-   **`kitshn.properties`**: Upstream configuration for services like ACRA crash reporting, funding links, and share wrapper URLs.
-   **`androidApp/build.gradle.kts`**: Gradle build script for the Android application module.
-   **`shared/build.gradle.kts`**: Gradle build script for the shared Kotlin Multiplatform module.
-   **`shared/schemas/de.kitshn.AppDatabase/1.json`**: Defines the database schema for the application.
-   **`fastlane/`**: Directory containing Fastlane automation scripts for mobile releases.
-   **`flatpak/app.kitshn.kitshn.yml`**: Flatpak manifest for desktop application packaging.
-   **`.github/workflows/*.yml`**: GitHub Actions workflow definitions for continuous integration and deployment tasks.
-   **`e2e/mtls-test-server/`**: Contains scripts and configurations (`docker-compose.yml`, `nginx.conf`, `setup-certs.sh`) for an mTLS end-to-end testing server environment.
-   **`web/`**: Contains the VitePress website configuration (`.vitepress/config.mts`), markdown content (`web/*.md`, `web/docs/`), and static assets (`web/public/`).
-   **`.gitleaks.toml`, `.gitleaksignore`**: Configuration files for gitleaks, used for secret scanning.

## 4. Dependencies
-   **Kotlin Multiplatform**: Core framework for cross-platform development.
-   **Jetpack Compose / Compose Multiplatform**: UI toolkit for Android, Desktop, and shared UI.
-   **Material 3 Expressive**: Design system for the UI.
-   **Gradle**: Build automation system.
-   **Fastlane**: Mobile CI/CD toolchain for Android and iOS.
-   **GitHub Actions**: CI/CD platform for automated builds, tests, and deployments.
-   **Tandoor Recipes (backend)**: The external self-hostable application this client interacts with.
-   **VitePress**: Static site generator for the project's documentation website.

## 5. Common Tasks
-   **Building Android Debug APK**: Run `./gradlew :androidApp:assembleDebug`. The output APK is in `androidApp/build/outputs/apk/debug/`.
-   **Syncing with Upstream**: `git fetch upstream && git merge upstream/main` (on a feature branch, then PR).
-   **Creating Pull Requests**: When creating a PR for this fork, always specify `--repo festion/kitshn --base main`.
-   **Secret Scanning**: GitHub Actions workflow `.github/workflows/secret-scan.yml` is run on pushes to `main` and PRs.
-   **Translation**: Contributions are managed on Weblate (hosted.weblate.org/projects/kitshn/).
-   **Website Management**: Content under the `web/` directory is built and served by VitePress.
