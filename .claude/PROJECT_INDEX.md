# Project Index: kitshn
## 1. Core Purpose
The `kitshn` project is an unofficial multiplatform client for the self-hostable Tandoor Recipes application, built using Kotlin Multiplatform (Compose). It aims to provide a modern, intuitive, and responsive user experience across Android, iOS, and Desktop platforms, with this particular fork (`festion`) including custom adjustments.

## 2. Architecture
The application leverages Kotlin Multiplatform with Compose for shared UI and business logic, enabling native applications on Android, iOS, and Desktop. Key architectural components include:
- **`shared` module**: Contains common Kotlin code, core logic, and database schemas.
- **`composeApp` module**: Holds common Compose Multiplatform UI code and resources.
- **`androidApp`**: Android-specific implementation.
- **`iosApp`**: iOS-specific implementation.
- **`desktopApp`**: Desktop-specific implementation.
- **`web`**: A VitePress-based documentation website.
- **`e2e/mtls-test-server`**: A Docker-compose based setup for end-to-end testing, including Nginx.
- **Build System**: Primarily Gradle (Kotlin DSL) for all platforms.
- **CI/CD**: Managed by GitHub Actions workflows (`.github/workflows`).
- **Mobile Automation**: Fastlane (`fastlane`) is used for release automation and metadata management.

## 3. Key Files
- `README.md`: Project overview, installation, features, and screenshots.
- `CLAUDE.md`: Specific project instructions for the `festion` fork, including remote/branch strategy, build details, and inherited configurations.
- `build.gradle.kts` (root, `androidApp`, `desktopApp`, `shared`): Gradle build configurations for the respective modules.
- `gradle/libs.versions.toml`: Centralized dependency versions for Gradle.
- `kitshn.properties`: Configuration properties for the application, including ACRA crash reporting and upstream service URLs.
- `.gitleaks.toml`, `.gitleaksignore`: Configuration for secret scanning.
- `.github/workflows/`: GitHub Actions workflows for distribution, nightly builds, secret scanning, and web deployments.
- `fastlane/Fastfile`: Fastlane entry point for mobile release automation.
- `fastlane/metadata/changelog.md`: Changelog for mobile app releases.
- `flatpak/app.kitshn.kitshn.yml`: Flatpak build configuration.
- `e2e/mtls-test-server/docker-compose.yml`: Defines the services for the end-to-end test server (Nginx, etc.).
- `web/package.json`: Dependencies and scripts for the web documentation.
- `web/.vitepress/config.mts`: Configuration for the VitePress documentation site.
- `shared/schemas/de.kitshn.AppDatabase/1.json`: Database schema definition.
- `shared/src/commonMain/composeResources/files/aboutlibraries.json`, `social_media_import_script.js`: Shared assets and scripts.

## 4. Dependencies
- **Kotlin Multiplatform & Compose Multiplatform**: Core framework for cross-platform development.
- **Gradle**: Build automation system.
- **Tandoor Recipes**: The backend application that `kitshn` clients interact with.
- **Fastlane**: Ruby-based toolchain for iOS and Android deployment.
- **VitePress**: Static site generator for the project's web documentation.
- **Weblate**: External platform for localization (l10n).
- **ACRA**: Android Application Crash Report Analyser, used for crash reporting (configured via `kitshn.properties`).
- **Docker/Nginx**: Used in the `e2e/mtls-test-server` for testing.

## 5. Common Tasks
- **Syncing Upstream**: To merge changes from the original `kitshn-app/kitshn` repository: `git fetch upstream && git merge upstream/main`.
- **Building Android Debug APK**: `./gradlew :androidApp:assembleDebug`. The output APK is located in `androidApp/build/outputs/apk/debug/`. Note that `debug` or `nightly` builds should be used for local development to avoid conflicts with Play Store versions.
- **Committing Changes**: Use `git pushx` to regenerate `.claude/PROJECT_INDEX.md` and push.
- **Creating Pull Requests**: When creating a PR for the `festion/kitshn` fork, always specify the target repository and base branch: `gh pr create --repo festion/kitshn --base main`.
