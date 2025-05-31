# MyCard

**MyCard** is an Android application designed as a lightweight mobile client for managing bank cards and tracking balances in multiple currencies. The application provides real-time conversion based on official exchange rates and allows users to select and view details of their bank cards.

## Features

- **Currency Conversion**
    - Instantly convert account balance and transaction history into GBP, EUR, or RUB
    - Conversion based on current exchange rates from the Central Bank of Russia ([API 1](https://www.cbr-xml-daily.ru/daily_json.js))
    - Base currency: USD

- **Card Selection**
    - Tap the card image to navigate to the card selection screen
    - Select a different card from the list ([API 2](https://hr.peterpartner.net/test/android/v1/users.json))
    - Automatically updates card data on the main screen

## UI Design

- UI fully corresponds to the provided **Figma** design
- SVG icons, typography, paddings, and layout dimensions taken directly from the design
- Adaptive layout for consistent behavior across screen sizes

## Architecture

- MVVM pattern with separation of concerns
- Lifecycle-aware components using Android Jetpack libraries

## Tech Stack

- **Language**: Kotlin
- **Min SDK**: 24 (Android 7.0)
- Android Architecture Components (ViewModel, LiveData)
- Retrofit for networking
- Gson for JSON parsing
- Material Components
- ViewBinding

## APIs Used

- **API 1: Currency Rates**
    - [https://www.cbr-xml-daily.ru/daily_json.js](https://www.cbr-xml-daily.ru/daily_json.js)
- **API 2: Card Holders**
    - [https://hr.peterpartner.net/test/android/v1/users.json](https://hr.peterpartner.net/test/android/v1/users.json)

## Offline Handling and UX

- Graceful behavior without internet connectivity
- Shows cached or default state before remote data arrives
- Designed for responsive interaction even with slow data fetching

## Screenshots
![image](https://github.com/user-attachments/assets/fddfb418-0d5f-464c-b52d-cb8dddf28e04)
![image](https://github.com/user-attachments/assets/050cd1ad-2aba-4852-bcfd-81c599697e79)
![image](https://github.com/user-attachments/assets/e0f7a07d-a482-4388-908b-662633f0d17f)



