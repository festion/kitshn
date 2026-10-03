# Project Index: kitshn
## 1. Core Purpose
The `kitshn` project is a multiplatform client for the self-hostable Tandoor Recipes application. This particular repository is a fork (festion/kitshn) of the official `kitshn-app/kitshn`, allowing for custom adjustments. It aims to provide a modern, intuitive, and responsive user experience across Android, iOS, and Desktop platforms using Kotlin Multiplatform and Material 3 Expressive.

## 2. Architecture
`kitshn` is built with Kotlin Multiplatform (Compose), featuring shared logic and UI components across different platforms.

Key architectural components include:
*   **`shared`**: Contains common Kotlin Multiplatform code, including business logic, data models, and database schemas.
*   **`composeApp`**: Holds shared Compose UI logic that is adapted for different platforms.
*   **`androidApp`**: Android-specific implementation, including `AndroidManifest.xml`, `build.gradle.kts`, and resources.
*   **`iosApp`**: iOS-specific implementation, including `Info.plist`, entitlements, and Swift UI components.
*   **`desktopApp`**: Desktop-specific implementation, including `build.gradle.kts` and packaging configurations.
*   **`web`**: Documentation and website content, built with VitePress.
*   **`fastlane`**: Automation for mobile releases, screenshots, and metadata management.
*   **`.github/workflows`**: GitHub Actions for Continuous Integration (CI), including Android debug builds, desktop distributions, Flatpak, nightly builds, and secret scanning.
*   **`e2e/mtls-test-server`**: End-to-end testing environment with Docker Compose, Nginx, and certificate setup scripts.

The application leverages Material 3 Expressive for its UI design across platforms.

## 3. Key Files
*   **`./README.md`**: Main project README, detailing installation, features, and impressions.
*   **`./CLAUDE.md`**: Fork-specific instructions, build notes, remote configuration, and fleet conventions.
*   **`.claude/PROJECT_INDEX.md`**: This project index, regenerated on `git pushx`.
*   **`./build.gradle.kts`**: Root Gradle build script.
*   **`./gradle/libs.versions.toml`**: Centralized dependency versions for Gradle modules.
*   **`./gradlew`, `./gradlew.bat`**: Gradle wrapper scripts for consistent builds.
*   **`./kitshn.properties`**: Configuration for upstream services like ACRA crash reporting and funding links.
*   **`./androidApp/build.gradle.kts`**: Android application build script.
*   **`./iosApp/iosApp.swift`**: Main iOS application entry point.
*   **`./shared/build.gradle.kts`**: Shared module build script.
*   **`./shared/schemas/de.kitshn.AppDatabase/1.json`**: Database schema definition.
*   **`./web/package.json`, `./web/package-lock.json`**: Web project dependencies.
*   **`./web/.vitepress/config.mts`**: VitePress configuration for the documentation website.
*   **`./.github/workflows/*.yml`**: GitHub Actions workflow definitions (e.g., `android-debug.yml`, `nightly.yml`, `secret-scan.yml`).
*   **`./.gitleaks.toml`**: Gitleaks configuration for secret scanning.
*   **`./.gitleaksignore`**: Gitleaks exemptions for known false positives.
*   **`./fastlane/Fastfile`**: Fastlane automation script.
*   **`./fastlane/metadata/changelog.md`**: Changelog managed by Fastlane.
*   **`./flatpak/app.kitshn.kitshn.yml`**: Flatpak build manifest.
*   **`./e2e/mtls-test-server/docker-compose.yml`**: Docker Compose configuration for E2E tests.

## 4. Dependencies
*   **Tandoor Recipes**: The primary external dependency, serving as the backend for the client application.
*   **Kotlin Multiplatform**: Core framework for cross-platform development.
*   **Compose Multiplatform**: UI framework for Android, Desktop, and Web.
*   **SwiftUI**: UI framework for iOS.
*   **Gradle**: Build automation tool (managed by `gradlew`).
*   **RubyGems/Bundler**: For `fastlane` and other Ruby-based tooling (via `Gemfile`, `Gemfile.lock`).
*   **GitHub Actions**: For CI/CD workflows.
*   **Weblate**: For localization efforts.
*   **VitePress**: Static site generator for the project website (`web` directory).
*   **Docker/Docker Compose**: Used in the E2E test environment.

## 5. Common Tasks
*   **Building Android Debug APK**: Run `./gradlew :androidApp:assembleDebug`. The output APK is found in `androidApp/build/outputs/apk/debug/`.
*   **Syncing with Upstream**: To integrate changes from the original `kitshn-app/kitshn` repository, execute `git fetch upstream && git merge upstream/main`.
*   **Creating Pull Requests**: When creating a PR for the `festion/kitshn` fork, always specify the target repository and base branch: `gh pr create --repo festion/kitshn --base main`.
*   **Regenerating Project Index**: The `git pushx` command automatically regenerates the `.claude/PROJECT_INDEX.md` file before pushing.
*   **Contributing Translations**: Help translate the application on the project's Weblate instance.
