# KMP Clean Architecture Template

A Kotlin Multiplatform starter project for exploring a modular Clean Architecture setup across Android and iOS.

The repository is intended as a starting point to study the project structure and adapt it to an application's needs.

## Architecture at a glance

The project separates presentation, domain, and data responsibilities, with shared code organized into reusable modules.

### Main modules

| Module | Responsibility |
|---|---|
| `composeApp` | Application entry point and feature integration |
| `core` | Shared core utilities, UI components, and domain models |
| `core-data` | Data-layer implementations |
| `core-local-datasource` | Local data access |
| `network` | Network communication |
| `feature-onboarding` | Onboarding screens and flows |
| `feature-home` | Home feature |
| `ui-alert` | Reusable alert and notification UI |
| `iosApp` | iOS application entry point |

See the [architecture overview](project_architecture.md) for the project diagram.

## Main technologies

- Kotlin Multiplatform
- Compose Multiplatform
- MVVM
- Koin for dependency injection
- Kotlin Coroutines and Flow
- Compose Navigation
- Ktor for networking

## Getting started

1. Clone this repository.
2. Open it in Android Studio and allow Gradle to sync.
3. Run the Android app on an emulator or device.
4. For iOS, open the iOS project in Xcode and run it on a simulator or device.

Use the Gradle wrapper included in the repository. You may need a compatible JDK, Android SDK, and Xcode installation for your development environment.

## Before using it in an application

Treat this as a starting point, not a drop-in production architecture. Review the modules, replace example flows, configure environment-specific values, and add the tests and platform integrations your application requires.

## License

See the repository's [LICENSE](LICENSE) file.
