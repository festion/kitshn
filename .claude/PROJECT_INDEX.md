# Project Index: kitshn
## 1. Core Purpose
Kitshn is an unofficial multiplatform client for the self-hostable Tandoor Recipes application, built with Kotlin Multiplatform (Compose) and Material 3 Expressive. This specific fork (`festion/kitshn`) includes custom adjustments for personal use, primarily targeting Android, iOS, and Desktop.

## 2. Architecture
The project follows a multiplatform architecture using Kotlin Multiplatform and Jetpack Compose.
- **`shared`**: Contains common business logic, data models, and database schemas.
- **`composeApp`**: Houses common Compose UI components and logic that can be shared across platforms.
- **`androidApp`**: Android-specific application code and resources.
- **`iosApp`**: iOS-specific application code and resources.
- **`desktopApp`**: Desktop-specific application code and resources.
- **`web`**: Project documentation website, built with VitePress.
- **`e2e/mtls-test-server`**: Contains end-to-end testing infrastructure, including a Docker Compose setup for a test server.
- **`fastlane`**: Automates mobile application deployment tasks.
- **`.github/workflows`**: Defines CI/CD pipelines using GitHub Actions for various platforms and tasks (Android debug builds, desktop distribution, Flatpak, web deployment, secret scanning).

## 3. Key Files
- `.claude/PROJECT_INDEX.md`: This project index file.
- `README.md`: Project overview, installation instructions, and key features.
- `CLAUDE.md`: Fork-specific instructions, branch management, Android build details, and notes on custom changes.
- `build.gradle.kts`: Root Gradle build configuration for the multiplatform project.
- `gradle/libs.versions.toml`: Centralized dependency versions and plugin management for Gradle.
- `androidApp/build.gradle.kts`: Gradle build script for the Android application module.
- `desktopApp/build.gradle.kts`: Gradle build script for the Desktop application module.
- `iosApp/iosApp.swift`: The main entry point for the iOS application.
- `shared/build.gradle.kts`: Gradle build script for the shared module.
- `shared/schemas/de.kitshn.AppDatabase/1.json`: Schema definition for the application's database.
- `composeApp/src/commonMain/composeResources/`: Directory for common Compose resources (e.g., `aboutlibraries.json`, `social_media_import_script.js`).
- `.github/workflows/android-debug.yml`: GitHub Actions workflow for building Android debug APKs.
- `fastlane/Fastfile`: Fastlane configuration for mobile release automation.
- `web/package.json`: Node.js package configuration for the web documentation site.
- `web/.vitepress/config.mts`: Configuration for the VitePress-based website.
- `e2e/mtls-test-server/docker-compose.yml`: Defines the services for the E2E test server.

## 4. Dependencies
- **Kotlin Multiplatform**: Core framework for sharing code across platforms.
- **Jetpack Compose**: UI toolkit used for building the user interface across Android, iOS, and Desktop.
- **Gradle**: Build automation system.
- **Fastlane**: Tool for automating iOS and Android deployment.
- **VitePress**: Static site generator used for the `web` documentation.
- **Tandoor Recipes**: The self-hostable backend application that Kitshn clients interact with.
- Dependencies managed via `gradle/libs.versions.toml` (Kotlin, Compose, etc.) and `web/package.json` (Node.js packages).

## 5. Common Tasks
- **Syncing with upstream**: `git fetch upstream && git merge upstream/main` (on a feature branch).
- **Building Android Debug APK**: `./gradlew :androidApp:assembleDebug`.
- **Creating Pull Requests**: `gh pr create --repo festion/kitshn --base main` (to target the correct fork and branch).
- **Running Gradle tasks**: General build, clean, and test tasks using `./gradlew`.
- **Managing GitHub Actions**: Monitoring and triggering CI/CD workflows in `.github/workflows`.
