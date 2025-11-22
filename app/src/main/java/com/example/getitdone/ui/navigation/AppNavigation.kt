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
            com.example.getitdone.ui.dashboard.DashboardScreen(navController)
        }
        composable(Screen.TaskList.route) {
            com.example.getitdone.ui.task.TaskListScreen(navController)
        }
        composable(Screen.HabitList.route) {
            com.example.getitdone.ui.habit.HabitListScreen(navController)
        }
        composable(Screen.Pet.route) {
            com.example.getitdone.ui.pet.PetScreen(navController)
        }
        composable(Screen.Calendar.route) {
            com.example.getitdone.ui.calendar.CalendarScreen(navController)
        }
        composable(Screen.Mood.route) {
            com.example.getitdone.ui.mood.MoodScreen(navController)
        }
        composable(Screen.Motivation.route) {
            com.example.getitdone.ui.motivation.MotivationScreen(navController)
        }
        composable(Screen.Settings.route) {
            com.example.getitdone.ui.settings.SettingsScreen(navController)
        }
        composable(Screen.CategoryManagement.route) {
            com.example.getitdone.ui.category.CategoryManagementScreen(navController)
        }
        composable(Screen.DashboardConfig.route) {
            com.example.getitdone.ui.dashboard.DashboardConfigScreen(navController)
        }
        composable(
            route = Screen.Pomodoro.route,
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) {
            com.example.getitdone.ui.pomodoro.PomodoroScreen(navController)
        }
    }
}
