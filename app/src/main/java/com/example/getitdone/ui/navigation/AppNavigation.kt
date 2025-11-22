package com.example.getitdone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Text

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Dashboard.route) {
        composable(Screen.Dashboard.route) {
            // DashboardScreen(navController)
            Text("Dashboard Placeholder")
        }
        composable(Screen.TaskList.route) {
            com.example.getitdone.ui.task.TaskListScreen(navController)
        }
        composable(Screen.HabitList.route) {
            com.example.getitdone.ui.habit.HabitListScreen(navController)
        }
        composable(Screen.Pet.route) {
            // PetScreen(navController)
            Text("Pet Placeholder")
        }
        composable(Screen.Calendar.route) {
            // CalendarScreen(navController)
            Text("Calendar Placeholder")
        }
        composable(Screen.Mood.route) {
            // MoodScreen(navController)
            Text("Mood Placeholder")
        }
        composable(Screen.Settings.route) {
            // SettingsScreen(navController)
            Text("Settings Placeholder")
        }
    }
}
