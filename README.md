# Math Practice for Android

A native Kotlin practice app that organizes exercises into lessons, accepts multiple-answer responses, records attempts, and calculates progress.

## Portfolio highlights

- Kotlin Android application with controller, model, adapter, and view layers
- JSON-backed practice sets
- Dynamic answer fields for multi-part questions
- Attempt history and lesson scoring
- RecyclerView-based set, lesson, and problem navigation

## Sanitized content

The historical prototype was built around a commercial curriculum. All publisher names, book covers, copied solution material, package identifiers, and curriculum-specific files have been removed from this portfolio version. The included `foundations_practice.json` contains original synthetic exercises created solely to demonstrate the application architecture.

This project is not affiliated with or endorsed by any textbook publisher.

## Technology

- Kotlin
- Android SDK 32
- AndroidX
- Material Components
- JSON assets

## Running locally

Open the repository in Android Studio and allow Gradle to synchronize. Android Studio will create the machine-specific `local.properties`, which is intentionally excluded.

The project retains its original 2022-era Android toolchain for historical context. Updating the Gradle plugin, target SDK, and UI architecture would be appropriate in a future modernization pass.
