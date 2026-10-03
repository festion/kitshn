# Project Index: kitshn
## 1. Core Purpose
kitshn is an unofficial multiplatform client for the self-hostable Tandoor Recipes application. This fork (festion) makes adjustments to the app while maintaining the core functionality. It is built with Compose and Material 3 Expressive, targeting Android, iOS, and Desktop platforms.

## 2. Architecture
The codebase is structured as a Kotlin Multiplatform (Compose) project.
- **`shared`**: Contains common Kotlin Multiplatform code, including business logic and shared UI components, used across all platforms.
- **`composeApp`**: Likely contains the common Compose UI application entry point or shared UI resources.
- **`androidApp`**: Android-specific implementation, build configuration, and resources.
- **`iosApp`**: iOS-specific implementation, build configuration, and resources (SwiftUI integration).
- **`desktopApp`**: Desktop-specific implementation, build configuration, and resources.
- **`web`**: Static website and documentation built with VitePress.
- **`e2e/mtls-test-server`**: End-to-end testing environment with Docker Compose for setting up an mTLS test server.
- **`fastlane`**: Mobile automation scripts for Android (Fastfile, actions for versioning, screenshots, release notes).
- **`flatpak`**: Configuration and packaging for Flatpak distribution on Linux desktops.
- **`.github/workflows`**: Continuous Integration/Continuous Deployment (CI/CD) pipelines for Android debug builds, desktop distribution, Flatpak, nightly builds, and secret scanning.

## 3. Key Files
- **`README.md`**: Main project overview, installation, features, and screenshots.
- **`CLAUDE.md`**: Project instructions specific to the `festion` fork, including remote configurations, build notes, and Fleet conventions.
- **`build.gradle.kts` (root), `androidApp/build.gradle.kts`, `desktopApp/build.gradle.kts`, `shared/build.gradle.kts`**: Gradle build scripts for different modules.
- **`gradle/libs.versions.toml`**: Centralized dependency management for Gradle.
- **`kitshn.properties`**: Upstream configuration for crash reporting (ACRA), funding links, and share wrapper URL.
- **`.gitleaks.toml`, `.gitleaksignore`**: Configuration for `gitleaks` static analysis to prevent secret exposure.
- **`.github/workflows/*.yml`**: GitHub Actions workflows for CI/CD processes (e.g., `android-debug.yml`, `secret-scan.yml`).
- **`fastlane/Fastfile`**: Main Fastlane configuration for mobile release automation.
- **`flatpak/app.kitshn.kitshn.yml`**: Flatpak manifest for building and distributing the desktop application.
- **`web/.vitepress/config.mts`**: Configuration for the VitePress-based documentation website.
- **`shared/src/commonMain/composeResources/files/social_media_import_script.js`**: JavaScript for social media import functionality.

## 4. Dependencies
- **Gradle**: Build automation system for Kotlin Multiplatform.
- **Kotlin Multiplatform**: Core framework for shared logic.
- **Compose Multiplatform**: UI framework for shared UI.
- **Ruby / Bundler**: For managing Fastlane dependencies (specified in `Gemfile`, `Gemfile.lock`).
- **Node.js / npm**: For managing web project dependencies (`web/package.json`, `web/package-lock.json`).
- **Docker / Docker Compose**: For the e2e test server setup.

## 5. Common Tasks
- **Build Android Debug APK**: `./gradlew :androidApp:assembleDebug` (output in `androidApp/build/outputs/apk/debug/`).
- **Sync with Upstream**: `git fetch upstream && git merge upstream/main` (on a feature branch, then PR to `main`).
- **Create Pull Request**: `gh pr create --repo festion/kitshn --base main` (important to specify repo and base).
- **Run CI/CD**: Pushing to `main` or opening a PR triggers GitHub Actions workflows (e.g., `android-debug.yml` for debug APK artifact).
- **Localization**: Contribute translations on Weblate (https://hosted.weblate.org/projects/kitshn/).
- **Secret Scanning**: `.github/workflows/secret-scan.yml` runs on push to `main` and PRs.
- **Work Tracking**: Vikunja project `kitshn`.
- **Regenerate PROJECT_INDEX.md**: `git pushx` (regenerates `.claude/PROJECT_INDEX.md`).
