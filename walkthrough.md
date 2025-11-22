# Walkthrough - Get It Done

I have implemented the "Get It Done" Android application with all the requested features. Since I cannot build the APK directly, here are the steps to verify the application in Android Studio.

## Prerequisites
- Android Studio Hedgehog or newer.
- JDK 17.

## Setup Instructions
1.  **Open Project**: Open the `get-it-done` directory in Android Studio.
2.  **Sync Gradle**: Allow Android Studio to sync the project and download dependencies.
3.  **Run App**: Select an emulator or physical device and click "Run".

## Features to Verify

### 1. Dashboard
- **Verify**: The app launches to a Dashboard screen with a grid of options: To-Do List, Habit Tracker, My Pet, Calendar, Mood Tracker, Motivation, and Settings.

### 2. To-Do List
- **Action**: Click "To-Do List".
- **Verify**: You can see a list of tasks.
- **Action**: Click the FAB (+) to add a task. Enter title and description.
- **Verify**: The new task appears in the list.
- **Action**: Check the checkbox to mark as complete.
- **Action**: Click the delete icon to remove a task.

### 3. Habit Tracker
- **Action**: Click "Habit Tracker".
- **Verify**: You can see a list of habits.
- **Action**: Click FAB (+) to add a habit.
- **Action**: Click the checkmark icon on a habit to increment the streak.

### 4. Digital Pet
- **Action**: Click "My Pet".
- **Verify**: If no pet exists, you are prompted to "Adopt a Pet".
- **Action**: Name your pet and adopt.
- **Verify**: You see the pet's name, level, and stats (Hunger, Happiness, Experience).
- **Action**: Click "Feed" to increase Hunger (fullness) and Experience.
- **Action**: Click "Play" to increase Happiness and Experience (decreases Hunger).

### 5. Calendar
- **Action**: Click "Calendar".
- **Verify**: You see a monthly calendar view. You can navigate between months.

### 6. Emotion Tracker
- **Action**: Click "Mood Tracker".
- **Verify**: You see a history of mood logs.
- **Action**: Click FAB (+) to log a mood (1-5) and an optional note.

### 7. Motivation
- **Action**: Click "Motivation".
- **Verify**: You see a motivational quote.
- **Action**: Click "New Quote" to see a different one.

### 8. Settings & Tools
- **Action**: Click "Settings".
- **Action**: Click "Backup to Cloud". Verify the success message after a delay.
- **Action**: Click "Export Data". Verify the success message after a delay.
- **Action**: Click "Manage Categories".
- **Verify**: You can add and delete categories.
- **Action**: Under "Appearance", select "Dark" or "Light".
- **Verify**: The app theme changes immediately.
- **Action**: Click "Customize Dashboard".
- **Action**: Toggle off "Calendar".
- **Action**: Go back to Dashboard.
- **Verify**: The "Calendar" card is hidden.

### 9. Pomodoro Timer
- **Action**: Go to "To-Do List".
- **Verify**: Each task has a "Play" (Pomodoro) icon.
- **Action**: Click the "Play" icon on a task.
- **Verify**: Navigates to the Pomodoro Timer screen.
- **Action**: Click "Start".
- **Verify**: Timer starts counting down.
- **Action**: Click "Pause" and "Reset".
- **Verify**: Timer pauses and resets to 25:00.
- **Note**: When the timer finishes, the task's Pomodoro count will increment (visible in the task list).

## Architecture Notes
- **MVVM**: Used for all screens.
- **Room Database**: Persists Tasks, Habits, Pet State, Mood Logs, and Categories.
- **DataStore**: Persists Theme and Dashboard Config.
- **Hilt**: Handles dependency injection.
- **Compose**: Fully declarative UI.
- **Migrations**: Infrastructure set up in `Migrations.kt` for future schema changes.
