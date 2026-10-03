# Project Index: kitshn
## 1. Core Purpose
kitshn is an unofficial, multiplatform (Android, iOS, Desktop) client for the self-hostable Tandoor Recipes application. It is built with Kotlin Multiplatform, Compose, and Material 3 Expressive, focusing on a modern, intuitive, and responsive user experience. This specific repository (`festion/kitshn`) is a fork of the main project, used for applying custom adjustments and maintaining a personalized version of the client.

## 2. Architecture
The project employs a Kotlin Multiplatform (KMP) architecture, sharing business logic and common UI elements across Android, iOS, and Desktop platforms.
- **`shared/`**: Contains core business logic and common components written in Kotlin, accessible by all platforms.
- **`composeApp/`**: Holds common Compose Multiplatform UI resources.
- **`androidApp/`**: Android-specific application module, utilizing Jetpack Compose.
- **`iosApp/`**: iOS-specific application module, integrating with Swift/SwiftUI.
- **`desktopApp/`**: Desktop-specific application module, also using Compose for desktop.
- **`web/`**: Houses the project's documentation website, built with VitePress (Node.js).
- **Build System**: Primarily uses Gradle, with `build.gradle.kts` files defining configurations for each module.
- **CI/CD**: GitHub Actions (`.github/workflows/`) manages builds, releases, and various checks across platforms.
- **Testing**: Includes `e2e/` for end-to-end testing, specifically for an mTLS test server setup.

## 3. Key Files
- **Project Root**:
    - `build.gradle.kts`: Root Gradle build script.
    - `gradle.properties`: Global Gradle properties.
    - `kitshn.properties`: Project-specific configuration, some from upstream.
    - `README.md`: Main project overview and installation instructions.
    - `CLAUDE.md`: Fork-specific project instructions, including remote setup, build notes, and local modifications.
    - `.gitleaks.toml`, `.gitleaksignore`: Configuration for gitleaks secret scanning.
- **Gradle Configuration**:
    - `gradle/libs.versions.toml`: Centralized dependency versions.
- **Application Modules**:
    - `androidApp/build.gradle.kts`: Android module build configuration.
    - `androidApp/src/main/AndroidManifest.xml`: Android application manifest.
    - `desktopApp/build.gradle.kts`: Desktop module build configuration.
    - `iosApp/iosApp/iosApp.swift`: iOS application entry point.
    - `shared/build.gradle.kts`: Shared module build configuration.
    - `shared/src/commonMain/`: Contains common Kotlin Multiplatform source code.
    - `composeApp/src/commonMain/composeResources/`: Common Compose Multiplatform resources.
- **CI/CD & Automation**:
    - `.github/workflows/*.yml`: GitHub Actions workflow definitions (e.g., `android-debug.yml`, `dist_desktop.yml`).
    - `fastlane/`: Directory for Fastlane mobile release automation scripts and metadata.
    - `fastlane/metadata/changelog.md`: Changelog for releases.
    - `flatpak/app.kitshn.kitshn.yml`: Flatpak application definition.
- **End-to-End Testing**:
    - `e2e/mtls-test-server/docker-compose.yml`: Docker Compose setup for mTLS test server.
    - `e2e/mtls-test-server/README.md`: Instructions for the mTLS test server.
    - `e2e/mtls-test-server/setup-certs.sh`, `e2e/mtls-test-server/seed-user.sh`: Scripts for test server setup.
- **Web Documentation**:
    - `web/package.json`: Node.js dependencies for the web project.
    - `web/.vitepress/config.mts`: VitePress configuration for the documentation site.
    - `web/docs/`: Contains various documentation markdown files.

## 4. Dependencies
- **Primary Technologies**: Kotlin Multiplatform, Jetpack Compose, Material 3 Expressive.
- **Build Tools**: Gradle, Gradle Wrapper.
- **Release Automation**: Fastlane (Ruby-based, managed via `Gemfile`), GitHub Actions.
- **Web Documentation**: Node.js, npm, VitePress (as indicated by `web/package.json`).
- **External APIs/Services**: Tandoor Recipes (backend system), Weblate (for localization), GitHub (for funding, issue templates).
- **Libraries**: Dependencies are managed via `gradle/libs.versions.toml` and specified in individual `build.gradle.kts` files.
- **Security**: Gitleaks for secret detection.

## 5. Common Tasks
- **Build Android Debug APK**: Execute `./gradlew :androidApp:assembleDebug` to build a debug APK, output to `androidApp/build/outputs/apk/debug/`.
- **Sync with Upstream**: To integrate changes from the original `kitshn-app/kitshn` repository, use `git fetch upstream && git merge upstream/main` on a feature branch, then create a PR to `festion/kitshn`'s `main` branch.
- **Maintain Fork-Specific Changes**: When merging upstream, ensure local modifications marked with "festion fork:" comments are preserved.
- **Run GitHub Actions**: Push to `main` or open PRs to trigger CI/CD workflows for Android debug APK uploads, desktop distribution, etc.
- **Manage Mobile Releases**: Utilize Fastlane tools (e.g., `fastlane/Fastfile`) for mobile application releases, including version changes and metadata updates.
- **Update Web Documentation**: Edit markdown files within the `web/` directory and use Node.js/VitePress commands to build and serve the site.
- **Contribute Translations**: Engage with the Weblate platform for localization efforts.
