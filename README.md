# 🧩 Pokedex Android App (Kotlin - Jetpack Compose)

A simple and clean Pokedex app built with **Kotlin**, following **modern Android development practices**. The app displays a list of Pokémon fetched from the [PokeAPI](https://pokeapi.co/), allows real-time search, and shows detailed information for each Pokémon.

---

## 🚀 Features

- Fetch and display Pokémon list from PokeAPI
- Real-time search functionality
- Detail view for each Pokémon (image, name, abilities)
- Snackbar for search errors (e.g. "not found")
- Modern UI with **Material 3** and **Jetpack Compose**
- Clean architecture with proper separation of concerns

---

## 🧱 Tech Stack & Architecture

| Layer             | Technology                      |
|------------------|----------------------------------|
| Design Pattern    | MVVM (ViewModel + StateFlow)    |
| UI                | Jetpack Compose + Material 3    |
| State Management  | ViewModel + StateFlow           |
| Networking        | Retrofit (PokeAPI)              |
| Image Loading     | Coil                            |
| Navigation        | Jetpack Navigation Compose      |
| Local Storage     | *(Optional)* Room (currently disabled) |

---

## ✅ Requirements Checklist

| No | Requirement                            | Status  | Description |
|----|----------------------------------------|---------|-------------|
| 1  | MVVM Architecture                      | ✅      | ViewModel and Repository implemented |
| 2  | External API integration               | ✅      | Uses PokeAPI via Retrofit |
| 3  | Reactive State Management              | ✅      | StateFlow + ViewModel |
| 4  | Modern UI (Jetpack Compose + M3)       | ✅      | Fully composed UI |
| 5  | Navigation between screens             | ✅      | Jetpack Navigation Compose |
| 6  | Error handling via Snackbar            | ✅      | Appears when Pokémon is not found |
| 7  | Project structure follows clean layering | ✅    | Organized into `Presentation`, `Data`, `Model` |
| 8  | Offline Storage (Room)                 | ❌      | Rolled back (not implemented in final version) |

---

## 🧪 How to Run

1. Clone this repository
2. Open it in **Android Studio Meerkat (Stable)** or higher
3. Ensure you are using **AGP 8.1+** and **Gradle 8.0+**
4. Build and run on emulator or physical device (API 21+)

---

### 📝 Demo Video
[Watch on Google Drive](https://drive.google.com/file/d/1_JVGOteoMukgiNAZWYs34SrKEaTemuZ5/view?usp=sharing)

---

## 📸 Screenshots

| Screen | Preview | Screen | Preview |
|--------|---------|--------|---------|
| LOG IN | <img src="https://drive.google.com/uc?id=1EY51yzx1I7KV8MrzIu1S6EPpa9uXmA0B" width="250" /> | SIGN UP | <img src="https://drive.google.com/uc?id=1ZVM8WDM8LG-xzBCG6XKtEOrw1YlJR99u" width="250" /> |
| HOME | <img src="https://drive.google.com/uc?id=19mU13pIrMz23wFLIAWco0tWk5i3hImId" width="250" />| DETAIL POKEMON | <img src="https://drive.google.com/uc?id=1zsl0k_b2Z95uRhySd5fO7E5BtnXJqsnY" width="250" /> |
| PROFILE | <img src="https://drive.google.com/uc?id=1_NSPf3NuNkt5Y7NEbWpVWrBa-5CSbbwr" width="250" /> | SEARCH FEATURE | <img src="https://drive.google.com/uc?id=1dVPOV9WTW-ZjKdAIyPvVb11Jk4PBjmLr" width="250" /> |

---

## ⏳ Development Duration

- Total development time: ~5 days
- Tools used: Android Studio Meerkat, PokeAPI, GitHub

---

## 🙌 Thanks!

Thanks for checking out this project! Feedback, suggestions, or even bug reports are warmly welcomed.  
Let’s catch ‘em all! 🔥
