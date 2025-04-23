# KMP Clean Architecture Template

A Kotlin Multiplatform project template that demonstrates Clean Architecture principles in a cross-platform mobile application targeting Android and iOS. This project uses Jetpack Compose for UI and follows modern development practices.

## Project Overview

This template provides a solid foundation for building cross-platform mobile applications with:

- **Kotlin Multiplatform**: Share code between Android and iOS
- **Jetpack Compose**: Modern declarative UI framework
- **Clean Architecture**: Separation of concerns with modular design
- **MVVM Pattern**: For presentation layer
- **Dependency Injection**: Using Koin
- **Navigation**: Compose Navigation for screen transitions
- **Reactive Programming**: Flow-based reactive architecture

## Architecture

The project follows Clean Architecture principles with a modular approach:

### Core Modules

- **core**: Contains common utilities, UI components, and domain models
- **core-data**: Data layer implementation
- **core-local-datasource**: Local data storage
- **network**: Network communication

### Feature Modules

- **feature-onboarding**: Onboarding and authentication screens
- **feature-home**: Main home screen functionality

### UI Modules

- **ui-alert**: Reusable alert and notification components

### App Module

- **composeApp**: Main application module that integrates all features

### Architecture Layers

1. **Presentation Layer**: UI components, ViewModels, and Navigation
2. **Domain Layer**: Business logic, use cases, and repository interfaces
3. **Data Layer**: Repository implementations, data sources, and models

## Project Structure

```
├── composeApp/                # Main application module
├── core/                      # Core functionality
├── core-data/                 # Data layer
├── core-local-datasource/     # Local data storage
├── network/                   # Network communication
├── ui-alert/                  # UI components for alerts
├── feature-onboarding/        # Onboarding feature
├── feature-home/              # Home screen feature
└── iosApp/                    # iOS application entry point
```

## Setup Instructions

### Prerequisites

- Android Studio Arctic Fox or newer
- Xcode 13 or newer (for iOS development)
- JDK 11 or newer
- Kotlin 1.8.0 or newer

### Building the Project

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the application on an Android device/emulator

### For iOS Development

1. Open the `iosApp/iosApp.xcodeproj` file in Xcode
2. Build and run the application on an iOS device/simulator

## Key Features

- Cross-platform code sharing
- Modular architecture
- Dependency injection
- Navigation system
- Theme support with dark mode
- Internet connectivity monitoring
- Alert system for notifications

## Dependencies

The project uses several key libraries:

- Jetpack Compose for UI
- Compose Navigation for navigation
- Koin for dependency injection
- Kotlin Coroutines and Flow for asynchronous programming
- Ktor for networking (implied by the network module)
- SQLDelight for local storage (implied by the core-local-datasource module)

## Contributing

Feel free to submit issues or pull requests to improve this template.

## License

[Add your license information here]

## Learn More

- [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
