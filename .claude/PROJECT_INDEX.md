# Project Index: kitshn
## 1. Core Purpose
kitshn is an unofficial multiplatform client for the self-hostable Tandoor Recipes application, built with Kotlin Multiplatform (Compose) and Material 3 Expressive. It aims to provide a modern, intuitive, and responsive user experience across Android, iOS, and Desktop platforms. This repository is a fork of the upstream `kitshn-app/kitshn` project, maintaining compatibility while incorporating specific adjustments and features.

## 2. Architecture
The project employs a Kotlin Multiplatform Mobile (KMM) architecture with Compose Multiplatform for UI.
- **`shared` module**: Contains common business logic, data models, and UI components that are shared across all platforms.
- **`androidApp`**: Android-specific application module, utilizing the shared module and potentially platform-specific Android UI or integrations.
- **`iosApp`**: iOS-specific application module, integrating the shared module for core logic.
- **`desktopApp`**: Desktop-specific application module (JVM), leveraging the shared module and Compose for Desktop.
- **`web`**: A separate web project, likely for documentation and project overview, built with VitePress (indicated by `.vitepress` directory and `package.json`).
- **`e2e/mtls-test-server`**: Contains infrastructure for end-to-end testing with mTLS, using Docker Compose.

## 3. Key Files
- **`CLAUDE.md`**: Project-specific instructions, fork details, remote configurations, building guidelines, and upstream configuration notes relevant to the `festion` fork.
- **`README.md`**: Main project overview, installation instructions, notable features, localization status, and impressions.
- **`.gitleaks.toml`**: Gitleaks configuration for scanning secrets.
- **`.gitleaksignore`**: Exemptions for Gitleaks scanning.
- **`.github/workflows/`**: Contains GitHub Actions workflows for CI/CD, including desktop/Flatpak distribution, nightly builds, web deployment, and secret scanning.
- **`gradle/libs.versions.toml`**: Centralized dependency management for Gradle modules using version catalogs.
- **`kitshn.properties`**: Configuration file containing properties for crash reporting, funding, and sharing wrapper URLs, often pointing to upstream services.
- **`androidApp/build.gradle.kts`**: Gradle build script for the Android application module.
- **`composeApp/src/commonMain/composeResources/`**: Location for common resources shared across Compose Multiplatform targets.
- **`desktopApp/build.gradle.kts`**: Gradle build script for the Desktop application module.
- **`iosApp/iosApp.swift`**: Main entry point for the iOS application.
- **`shared/build.gradle.kts`**: Gradle build script for the shared Kotlin Multiplatform module.
- **`shared/schemas/de.kitshn.AppDatabase/1.json`**: Database schema definition for the shared module.
- **`web/package.json`**: Node.js package configuration for the web project.
- **`web/.vitepress/config.mts`**: Configuration for the VitePress-based project website.
- **`fastlane/`**: Contains Fastlane configurations for mobile release automation, including actions for versioning, changelogs, and screenshots.

## 4. Dependencies
- **Kotlin Multiplatform**: Core framework for sharing code across platforms.
- **Compose Multiplatform**: UI framework for building native-looking UIs on Android, iOS, and Desktop from a single codebase.
- **Material 3 Expressive**: Design system used for UI aesthetics.
- **Gradle**: Build automation tool for Kotlin projects.
- **VitePress**: Static site generator for the project website (`web` module).
- **Fastlane**: Automation tool for iOS and Android app deployment.

## 5. Common Tasks
- **Building Android Debug APK**: Run `./gradlew :androidApp:assembleDebug` to generate a debug APK in `androidApp/build/outputs/apk/debug/`.
- **Syncing with Upstream**: Execute `git fetch upstream && git merge upstream/main` on a feature branch to pull changes from the original `kitshn-app/kitshn` repository.
- **Creating Pull Requests**: When creating a PR for the `festion/kitshn` fork, always specify the target repository and base branch: `gh pr create --repo festion/kitshn --base main`.
- **Pushing Changes**: Use `git pushx` as per fleet conventions, which regenerates the `.claude/PROJECT_INDEX.md` file.
- **Work Tracking**: Tasks are tracked in the Vikunja project `kitshn`.
- **Localization**: Contributions for translation are managed on Weblate.
