package com.example.getitdone.ui.navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object TaskList : Screen("task_list")
    object HabitList : Screen("habit_list")
    object Pet : Screen("pet")
    object Calendar : Screen("calendar")
    object Mood : Screen("mood")
    object Motivation : Screen("motivation")
    object Settings : Screen("settings")
    object CategoryManagement : Screen("category_management")
    object DashboardConfig : Screen("dashboard_config")
    object Pomodoro : Screen("pomodoro/{taskId}") {
        fun createRoute(taskId: Int) = "pomodoro/$taskId"
    }
}
