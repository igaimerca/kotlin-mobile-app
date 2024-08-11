# Ampersand Assignment

## Demo Video
<video width="320" height="240" controls>
  <source src="demo.webm" type="video/webm">
  Your browser does not support the video tag.
</video>

## Instructions

1. Clone the repository:
    ```bash
    git clone <https://github.com/igaimerca/kotlin-mobile-app>
    ```

2. Open the project in Android Studio.

3. Build and run the app on an Android device or emulator.

## Libraries Used

- Retrofit for network requests
- Gson for JSON parsing
- RecyclerView for displaying lists

## Assumptions and Design Decisions

- The app fetches data from `https://api.restful-api.dev/objects`.
- The app supports landscape layouts for tablets by providing dedicated XML layout files in `res/layout-land/`.

## Design Decisions

- Used Material Design guidelines for a modern look and feel.
- Added a "View More" button to the list view to toggle between a truncated list and the full list.
- Ensured the details view is centered and visually appealing with clean, centered text.