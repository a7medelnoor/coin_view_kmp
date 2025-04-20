# CoinView KMP - Cryptocurrency Tracker

<div align="center">
  <img src="assets/images/coin_view_logo.png" alt="CoinView Logo" width="200"/>
</div>

A modern cryptocurrency tracking application built with Kotlin Multiplatform (KMP) that runs on Android, iOS, and Web platforms. The app provides real-time cryptocurrency market data, price tracking, and detailed coin information.

## Demo

### Android
<div align="center">
  <img src="https://raw.githubusercontent.com/a7medelnoor/coin_view_kmp/main/assets/demo/android_demo.gif" alt="Android Demo" width="600"/>
</div>

### iOS
<div align="center">
  <img src="https://raw.githubusercontent.com/a7medelnoor/coin_view_kmp/main/assets/demo/ios_demo.gif" alt="iOS Demo" width="600"/>
</div>

## Features

- 📊 Real-time cryptocurrency market data
- 🔍 Search functionality for cryptocurrencies
- 📈 Detailed price history charts
- 💰 Market statistics and trends
- 🌐 Cross-platform support (Android, iOS, Web)
- 🎨 Modern UI with Compose Multiplatform

## Project Structure

```
├── composeApp/              # Shared code across platforms
│   ├── src/
│   │   ├── commonMain/      # Shared Kotlin code
│   │   ├── androidMain/     # Android-specific code
│   │   ├── iosMain/         # iOS-specific code
│   │   └── wasmJsMain/      # Web-specific code
├── iosApp/                  # iOS application entry point
└── androidApp/              # Android application entry point
```

## Technologies Used

- Kotlin Multiplatform (KMP)
- Compose Multiplatform for UI
- Ktor for networking
- Kotlin Serialization
- Kotlin Coroutines
- Koin for dependency injection

## Architecture

<div align="center">
  <img src="assets/images/kmp_architecture_diagram.png" alt="Project Architecture" width="600"/>
</div>

The app follows Clean Architecture and MVVM (Model-View-ViewModel) patterns, ensuring a robust, maintainable, and testable codebase:

### Clean Architecture Layers

1. **Data Layer**
   - Remote data sources (API calls)
   - Repository implementations
   - Data mappers (DTO to Domain)
   - Local data sources (if needed)

2. **Domain Layer**
   - Business logic and use cases
   - Repository interfaces
   - Domain models
   - Business rules and validations

3. **Presentation Layer**
   - ViewModels
   - UI State management
   - Screen composables
   - Navigation

### Key Architectural Features

- 🧩 Clear separation of concerns
- 🔄 Unidirectional data flow
- 🧪 Highly testable components
- 🔒 Dependency injection for loose coupling
- 📱 Platform-agnostic business logic
- 🚀 Scalable and maintainable structure

## Setup

1. Clone the repository
2. Open the project in Android Studio or IntelliJ IDEA
3. For iOS development:
   - Open the `iosApp` folder in Xcode
   - Select your target device/simulator
   - Build and run the app
4. For Android development:
   - Open the project in Android Studio
   - Select your target device/emulator
   - Build and run the app
5. For Web development:
   - Run the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task

## API Key Setup

1. Get your API key from [CoinCap API](https://docs.coincap.io/)
2. Create a `secrets.properties` file in the root directory
3. Add your CoinCap API key:
   ```
   COINCAP_API_KEY=your_api_key_here
   ```

## Contributing

Feel free to submit issues and enhancement requests!

## License

This project is licensed under the MIT License - see the LICENSE file for details.