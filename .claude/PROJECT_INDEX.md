# Project Index: kitshn
## 1. Core Purpose
`kitshn` is a Kotlin Multiplatform (Compose) client for the self-hostable **Tandoor Recipes** application. This repository is a fork of the official `kitshn-app/kitshn` project, maintained by `festion` to apply specific adjustments. The application aims to provide a modern, intuitive, and responsive user experience across Android, iOS, and Desktop platforms, offering features like recipe management, meal planning, shopping lists, and social media importing.

## 2. Architecture
The project is built using **Kotlin Multiplatform** with **Compose** and **Material 3 Expressive** for the UI. It targets Android, iOS, and Desktop platforms.
- **`shared`**: Contains common Kotlin Multiplatform code, including application logic and database schemas.
- **`composeApp`**: Holds common Compose UI code that is shared across platforms.
- **`androidApp`**: Android-specific application module.
- **`iosApp`**: iOS-specific application module.
- **`desktopApp`**: Desktop-specific application module.
- **`web`**: Hosts the project's documentation and static site.

## 3. Key Files
- **`README.md`**: Main project overview, installation instructions, features, and screenshots.
- **`CLAUDE.md`**: Project instructions specific to the `festion` fork, detailing remotes, build processes for the phone, and upstream configuration notes (e.g., ACRA crash collector being disabled).
- **`.github/workflows/`**: GitHub Actions workflows for CI/CD, including Android debug builds, desktop distributions, Flatpak builds, nightly builds, and secret scanning.
- **`androidApp/`**: Android application source, `build.gradle.kts` for configuration, and `proguard-rules.pro`.
- **`composeApp/src/commonMain/composeResources/`**: Shared Compose resources, including `aboutlibraries.json` and `social_media_import_script.js`.
- **`desktopApp/`**: Desktop application source, build scripts, icons, and packaging configurations (e.g., AppImage).
- **`iosApp/`**: iOS application source, project configuration, and asset catalogs.
- **`shared/`**: Contains core multiplatform logic, `build.gradle.kts`, `proguard-rules.pro`, and database schemas under `schemas/`.
- **`web/`**: Markdown files for documentation (`contact.md`, `download.md`, `funding.md`, `index.md`, `screenshots.md`, `translate.md`, `docs/`) and `package.json` for web dependencies.
- **`fastlane/`**: Fastlane configurations for mobile automation, including actions for versioning, changelog updates, and screenshot taking.
- **`flatpak/`**: Flatpak manifest and related files for Linux desktop distribution.
- **`gradle/libs.versions.toml`**: Centralized dependency versions for Gradle.
- **`.gitleaks.toml`**: Gitleaks configuration for detecting sensitive information.
- **`kitshn.properties`**: Configuration file containing upstream service pointers and ACRA settings (ACRA is disabled in this fork).

## 4. Dependencies
- **Kotlin Multiplatform**: Core framework for cross-platform development.
- **Jetpack Compose**: UI toolkit for Android and Desktop, extended by Compose Multiplatform.
- **Fastlane**: For automating mobile release processes on Android and iOS.
- **Gradle**: Build automation system.
- **Tandoor Recipes**: The self-hostable backend application that `kitshn` clients interact with.
- **Weblate**: Used for localization and translation management.

## 5. Common Tasks
- **Syncing with upstream**: `git fetch upstream && git merge upstream/main` (to be performed on a feature branch, then PR to `festion/kitshn`).
- **Building Android debug APK**: `./gradlew :androidApp:assembleDebug` (output in `androidApp/build/outputs/apk/debug/`).
- **Creating Pull Requests**: PRs should be made against `festion/kitshn` and specify `--base main`.
- **Pushing changes**: Use `git pushx` to ensure `.claude/PROJECT_INDEX.md` is regenerated.
- **Secret Scanning**: `.github/workflows/secret-scan.yml` runs on push to `main` and PRs.
- **ACRA Crash Reporting**: Disabled in this fork; relevant code has `festion fork:` comments.
- **Work Tracking**: Managed via Vikunja project `kitshn`.
