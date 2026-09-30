# ✨ FluidFurnish - Android Motion & Animation Showcase

A sleek, production-ready Android application built with **Jetpack Compose**, demonstrating advanced motion design, fluid UI animations, and robust modern Android architecture.

---

## 🌟 Key Features

- **Sequential Cascading Animations:** Step-by-step orchestrated entry animations across the header, search bar, category chips, and product lists.
- **Shared Element Transitions:** Seamless layout bounds morphing from furniture collection cards into advanced full-screen detail overlays.
- **Parallax Card Carousel:** Custom overlapping, rotating (`rotationZ`), and scaling card animations.
- **Modern MVVM & UDF Architecture:** Built using Unidirectional Data Flow with a single source of truth (`HomeUiState`) managed via `HomeViewModel`.
- **Dependency Injection:** Powered by **Dagger Hilt**.
- **Performance & Testing:** Integrated with **LeakCanary** for memory leak detection and comprehensive **Jetpack Compose UI Tests**.

---

## 🛠️ Tech Stack

- **UI:** Jetpack Compose, Material 3, Compose Animation (Shared Element Transitions)
- **Architecture:** MVVM, Unidirectional Data Flow (UDF), StateFlow
- **Dependency Injection:** Dagger Hilt
- **Concurrency:** Kotlin Coroutines & Flow
- **Testing & Quality:** JUnit, Compose UI Test Rules (`createAndroidComposeRule`), LeakCanary

---

## 🚀 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/nitinpatil2000/ComposeMotion-Catalog.git
   ```
2. Open the project in **Android Studio** (Koala or newer recommended).
3. Sync Gradle and run the app on an emulator or physical device.

---

## 📱 Screenshots / Demo

*(Add your app demo GIFs or screenshots here)*

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
