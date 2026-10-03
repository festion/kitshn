# Project Index: kitshn
## 1. Core Purpose
This is a personal fork of `kitshn`, a Kotlin Multiplatform (Compose) client for the self-hosted Tandoor Recipes application. The main purpose of this fork is to introduce custom modifications for the operator's personal use, while tracking the upstream `kitshn-app/kitshn` repository for updates. The base application provides a modern, intuitive, and responsive user experience for interacting with a Tandoor instance across Android, iOS, and Desktop.

## 2. Architecture
The project follows a standard Kotlin Multiplatform structure, built with Gradle.
- **`shared`**: The core module containing common business logic, data models, and network code shared across all platforms.
- **`composeApp`**: Contains shared UI components written in Compose Multiplatform.
- **`androidApp`**, **`iosApp`**, **`desktopApp`**: Platform-specific modules that implement the platform-specific entry points and configurations.
- **`web`**: A VitePress-based website for documentation and project information.
- **CI/CD**: GitHub Actions are used for building, testing, and distributing the applications, particularly for generating debug Android APKs since the local development environment may lack the Android SDK.
- **Fork-specific Changes**: Key architectural changes in this fork include the disabling of the ACRA crash reporting system. All local modifications are marked with a `festion fork:` comment for easy identification during upstream merges.

## 3. Key Files
- `CLAUDE.md`: Authoritative documentation for this specific fork, detailing its purpose, build instructions, remotes, and a list of modifications made to the upstream code. **This is the most important file for understanding this fork.**
- `README.md`: The general README from the upstream project, describing the app's features and providing installation links.
- `build.gradle.kts` & `settings.gradle.kts`: The root Gradle build and settings files that define the project structure and dependencies.
- `gradle/libs.versions.toml`: The version catalog defining all project dependencies and their versions.
- `.github/workflows/android-debug.yml`: The GitHub Actions workflow that builds a debug APK on every push to `main`, which is the primary method of distribution for this fork.
- `shared/`: Directory containing the shared Kotlin Multiplatform code, which is the core of the application logic.
- `androidApp/src/main/kotlin/de/kitshn/android/AndroidApp.kt`: The Android application entry point. Contains a fork-specific modification to disable ACRA.

## 4. Dependencies
- **Kotlin Multiplatform**: The core technology for sharing code between platforms.
- **Compose Multiplatform**: Used for building the user interface across Android, iOS, and Desktop from a single codebase.
- **Tandoor Recipes**: The self-hosted recipe application that this client connects to as its backend. API specifics are documented separately.
- **Gradle**: The build automation tool used for the entire project.
- **Fastlane**: Used for automating build and release processes, particularly for mobile app metadata and screenshots.

## 5. Common Tasks
- **Building the Android Debug APK**: The preferred method is to rely on the CI workflow defined in `.github/workflows/android-debug.yml`. Every push to `main` builds the APK, signs it with a persistent debug key, and attaches it to a pre-release tag named `debug-latest`. The APK can be downloaded directly from the GitHub releases page for this fork. Locally, the command is `./gradlew :androidApp:assembleDebug`.
- **Syncing with Upstream**: To update the fork with changes from the original `kitshn-app/kitshn` repository:
  1. `git fetch upstream`
  2. `git merge upstream/main` (on a feature branch)
  3. Resolve any conflicts, paying attention to fork-specific changes marked with `festion fork:`.
  4. Create a pull request targeting the `main` branch of this fork (`festion/kitshn`).
