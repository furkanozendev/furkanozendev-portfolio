# furkanozendev

Engineering portfolio built with Kotlin and Compose Multiplatform targeting WebAssembly.

## Why this project exists

This project serves as a technical demonstration of Kotlin Multiplatform (KMP) in a production web environment. The objective was to move beyond the experimental nature of Wasm-based web applications and implement a stable, high-performance site using a shared architectural model. It explores the viability of `wasmJs` for technical portfolios where engineering depth and UI consistency across platforms are prioritized over traditional SEO-heavy web requirements.

## Tech stack

*   **Language**: Kotlin 2.1.0
*   **UI Framework**: Compose Multiplatform (wasmJs)
*   **Architecture**: Clean Architecture with Feature-based modularization
*   **Dependency Injection**: Koin
*   **Networking**: Ktor Client
*   **State Management**: kotlinx.coroutines StateFlow
*   **Hosting**: Cloudflare Pages

## Architecture overview

The application is structured into clearly defined layers to enforce separation of concerns and maintainability:

*   **Presentation**: MVVM-based UI logic using Compose. State is managed via `StateFlow`, ensuring a unidirectional data flow from the ViewModel to the Composable functions.
*   **Domain**: Contains business logic, entities, and repository interfaces. This layer is strictly independent of UI components and external frameworks.
*   **Data**: Implemented repositories, data sources (Ktor for remote, local providers), and DTOs.
*   **Core - Design System**: A dedicated module housing the theme, color tokens, and reusable UI components, ensuring visual consistency across the application.

## Web and WASM considerations

The choice of `wasmJs` over the traditional `js` target was driven by performance and the maturity of the Kotlin/Wasm toolchain. 

*   **Runtime Performance**: Wasm provides near-native execution speed, which is particularly noticeable in complex Compose animations and layout calculations.
*   **SEO and Hydration**: As `wasmJs` renders directly to a Canvas element via Skia, traditional SEO and hydration techniques do not apply. This is an intentional trade-off; the project focuses on execution quality and engineering demonstration rather than search engine discoverability.
*   **Binary Delivery**: The build processes the application into a compiled Wasm binary, significantly reducing the overhead compared to high-level JavaScript execution.

## What this project demonstrates

*   **Compose Multiplatform proficiency**: Advanced usage of the Compose runtime in a non-Android environment.
*   **Multi-module architecture**: Effective use of Kotlin project structuring and dependency management.
*   **Wasm delivery**: Knowledge of modern web deployment standards and performance optimization.
*   **CI/CD engineering**: Automated build and deployment pipelines for static web platforms.

## Live site

[furkanozen.com](https://furkanozen.com)

## Note

This is a personal portfolio and an open-source reference for Kotlin/Wasm development. Feel free to explore the architecture and implementation details.