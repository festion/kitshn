# Project Index: kitshn

## 1. Core Purpose

`kitshn` is a Kotlin Multiplatform (Compose) client for the Tandoor Recipes self-hosted application. This repository is a personal fork of the main `kitshn-app/kitshn` project, containing specific modifications for the operator's use. The app targets Android, iOS, and Desktop platforms.

## 2. Architecture

The project follows a standard Kotlin Multiplatform structure, managed by Gradle.
- **`shared/`**: Contains the core business logic, UI components (with Compose), and data layers common to all platforms.
- **`androidApp/`**, **`iosApp/`**, **`desktopApp/`**: Platform-specific modules that act as entry points and handle platform-specific implementations and packaging.
- **`web/`**: A VitePress-based documentation website for the project.
- **UI**: Built with Jetpack Compose and Material 3 Expressive.
- **Fork-specific changes**: This fork disables ACRA crash reporting and modifies UI layouts (e.g., ingredient order). These changes are marked with a `festion fork:` comment in the code.

## 3. Key Files

- **`CLAUDE.md`**: **Crucial read.** Contains instructions specific to this fork, including build procedures, CI details, remotes, and summaries of all local modifications.
- **`build.gradle.kts` & `settings.gradle.kts`**: Root Gradle configuration files for the multiplatform project.
- **`gradle/libs.versions.toml`**: The Gradle Version Catalog, defining all project dependencies and their versions.
- **`shared/src/commonMain/`**: Location of the primary shared Kotlin code (ViewModels, data models, Composables).
- **`.github/workflows/android-debug.yml`**: The primary CI workflow for building debug APKs for Android. Pushes to `main` update a `debug-latest` GitHub release.
- **`kitshn.properties`**: Configuration for upstream services, though some (like ACRA) are disabled in this fork.

## 4. Dependencies

- **Build System**: Gradle with Kotlin DSL.
- **Core Framework**: Kotlin Multiplatform.
- **UI**: Jetpack Compose for Multiplatform, Material 3 Expressive.
- **Dependency Management**: Dependencies are centralized in `gradle/libs.versions.toml`.

## 5. Common Tasks

- **Build a debug APK for Android**:
  ```bash
  ./gradlew :androidApp:assembleDebug
  ```
  Note: The primary build method is via CI as described in `CLAUDE.md`. The resulting APK is available as a GitHub release artifact.

- **List all fork-specific code modifications**:
  ```bash
  grep -rn "festion fork:" --include='*.kt' .
  ```

- **Sync with the upstream repository**:
  On a feature branch, run:
  ```bash
  git fetch upstream && git merge upstream/main
  ```
  Then open a PR against this fork's `main` branch.
