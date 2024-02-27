# Vlue Users Android App

## Overview

The Vlue Users Android App is a mobile application designed to fetch and display a list of users from the Random User API. The app follows Clean Architecture principles and SOLID principles to ensure clean, maintainable code. 
It utilizes the latest technologies such as Kotlin, Jetpack Compose for UI, Coroutines for asynchronous operations, Retrofit for network calls, and Hilt for dependency injection.

## Features

- Fetches users from the Random User API.
- Displays users in a paginated RecyclerView.
- Loads more users as the user scrolls.
- Allows users to view detailed information about each user.
- Follows Clean Architecture principles and SOLID principles.
- Uses the latest technologies such as Kotlin, Jetpack Compose, Coroutines, Retrofit, and Hilt.

## API Pagination

The app leverages the pagination feature of the Random User API. It requests multiple pages of a seed with the `page` parameter, ensuring that the same seed and page number (1-based index) are used to get back the same results.

## Dependencies

The Vlue Users Android App relies on the following dependencies:

- Kotlin
- Jetpack Compose
- Coroutines
- Retrofit
- Hilt

## Project Structure

com.vlueusers.model: Contains modules related to data handling, such as dependency injection, networking, repositories, and utilities.

- **di**: Contains modules for dependency injection setup.
  - `AppModule.kt`: Defines application-level dependencies.
  - `ContextModule.kt`: Provides application context.
  - `DataDownloaderModule.kt`: Configures dependencies related to data downloading.
  - `RepositoryModule.kt`: Sets up dependencies for repositories.

- **network**: Contains networking-related classes.
  - **entities**: Holds entity classes for network data mapping.
    - `User.kt`: Represents user data received from the API.
  - `ApiClient.kt`: Provides a Retrofit client setup.
  - `DataDownloader.kt`: Handles data downloading logic.
  - `UserService.kt`: Defines Retrofit service interface for user-related API endpoints.

- **repositories**: Contains classes responsible for data management.
  - `UserRepository.kt`: Implements the repository pattern for user data management.

- **utils**: Holds utility classes.
  - `DefaultPaginator.kt`: Provides a default implementation of a paginator.
  - `Paginator.kt`: Defines the paginator interface.

com.vlueusers.ui: Contains UI-related modules.

- **navigation**: Contains classes related to navigation setup.
  - `Navigation.kt`: Handles navigation logic.
  - `Screen.kt`: Defines screen navigation destinations.

- **theme**: Holds theme-related classes.
  - `Color.kt`: Defines color constants.
  - `Theme.kt`: Provides theme setup.
  - `Type.kt`: Defines typography constants.

- **utils**: Contains UI utility classes.
  - `TopBar.kt`: Provides a custom top bar implementation.

- `DetailsScreen.kt`: Represents the detail screen for displaying user details.
- `HomeScreen.kt`: Represents the home screen for displaying users in a paginated RecyclerView.

com.vlueusers.viewmodel: Contains ViewModel-related classes.
- `AppViewModel.kt`: Implements the application ViewModel.

`MainActivity.kt`: Entry point of the application.

### To set up and run the Vlue Users Android App on your local machine clone the repository
