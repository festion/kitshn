# Project Index: kitshn

## 1. Core Purpose
`kitshn` is an unofficial multiplatform client for the self-hostable Tandoor Recipes application. This specific fork (`festion/kitshn`) is used to make adjustments to the app, running on a Pixel phone against a homelab Tandoor instance. It aims to deliver a modern, intuitive, and responsive user experience across Android, iOS, and Desktop platforms using Compose and Material 3 Expressive.

## 2. Architecture
The project is structured as a Kotlin Multiplatform (Compose) application, with distinct modules for each target platform:
-   **`shared`**: Contains the core business logic, data models, and UI components written in Kotlin Multiplatform.
-   **`androidApp`**: Android-specific implementation and resources.
-   **`iosApp`**: iOS-specific implementation and resources.
-   **`desktopApp`**: Desktop-specific implementation and resources.
-   **`composeApp`**: Placeholder for common Compose UI.
-   **`web`**: Static website for documentation, screenshots, and downloads, built with VitePress.

The project is a fork of `kitshn-app/kitshn` with local modifications marked by "festion fork:" comments, primarily related to ACRA crash reporting and recipe step layout. Build system is Gradle Kotlin DSL.

## 3. Key Files
-   **`CLAUDE.md`**: Project instructions specific to the `festion` fork, detailing remotes, branches, build processes, upstream configuration overrides, and local changes.
-   **`README.md`**: Public-facing project overview, features, installation instructions, and screenshots.
-   **`build.gradle.kts` (root), `shared/build.gradle.kts`, `androidApp/build.gradle.kts`, `desktopApp/build.gradle.kts`**: Gradle build scripts defining project structure, dependencies, and build logic for different modules.
-   **`gradle/libs.versions.toml`**: Centralized dependency management for Gradle.
-   **`kitshn.properties`**: Configuration file for upstream-related settings (e.g., ACRA, funding URLs).
-   **`.github/workflows/*.yml`**: GitHub Actions workflows for CI/CD, including Android debug builds, desktop distributions, Flatpak, and secret scanning.
-   **`fastlane/`**: Contains Fastlane configurations for mobile release automation, including actions for versioning, changelog updates, and screenshot generation.
-   **`flatpak/app.kitshn.kitshn.yml`**: Flatpak manifest for desktop application distribution.
-   **`web/`**: Contains the source for the project's website, including markdown content for documentation (`web/docs/`) and various static assets.
-   **`e2e/mtls-test-server/`**: End-to-end testing setup for an mTLS test server, including `docker-compose.yml`, `nginx.conf`, and certificate setup scripts.

## 4. Dependencies
-   **Tandoor Recipes**: Backend API that the `kitshn` client consumes.
-   **Kotlin Multiplatform**: Core framework for shared logic.
-   **Jetpack Compose / Compose Multiplatform**: UI framework for all client applications.
-   **Material 3 Expressive**: Design system for the UI.
-   **Gradle**: Build automation tool.
-   **Fastlane**: Mobile release automation.
-   **VitePress**: Static site generator for the `web` documentation.
-   **Weblate**: For localization (l10n) efforts.

## 5. Common Tasks
-   **Building Android Debug APK**: Run `./gradlew :androidApp:assembleDebug`. The output APK is found in `androidApp/build/outputs/apk/debug/`.
-   **Syncing with Upstream**: `git fetch upstream && git merge upstream/main` (on a feature branch).
-   **Creating GitHub Pull Requests (fork)**: When creating a PR from this fork, always use `--repo festion/kitshn --base main` to target the `festion/kitshn` repository.
-   **Translation Contributions**: Via Weblate as mentioned in `README.md`.
-   **Managing App Releases**: Utilize `fastlane` scripts for mobile release processes.
