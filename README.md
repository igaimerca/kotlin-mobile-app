## Demo
[demo (1).webm](https://github.com/user-attachments/assets/ccbc0c14-f7a3-4f53-91b1-f881b87d65c1)

## Instructions

1. Clone the repository:
    ```bash
    git clone https://github.com/igaimerca/kotlin-mobile-app
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
- Added a "View More" button to the list view to toggle between a truncated list and the full list.
- Ensured the details view is centered and visually appealing.
- I assumed that api for getting object details is `https://api.restful-api.dev/objects\{id}`
