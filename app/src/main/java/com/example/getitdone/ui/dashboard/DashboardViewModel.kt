package com.example.getitdone.ui.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getitdone.data.repository.UserPreferencesRepository
import com.example.getitdone.ui.navigation.Screen
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val allItems = listOf(
        DashboardItem("todo", "To-Do List", Icons.Default.List, Screen.TaskList.route),
        DashboardItem("habit", "Habit Tracker", Icons.Default.CheckCircle, Screen.HabitList.route),
        DashboardItem("pet", "My Pet", Icons.Default.Face, Screen.Pet.route),
        DashboardItem("calendar", "Calendar", Icons.Default.DateRange, Screen.Calendar.route),
        DashboardItem("mood", "Mood Tracker", Icons.Default.Star, Screen.Mood.route),
        DashboardItem("motivation", "Motivation", Icons.Default.Favorite, Screen.Motivation.route),
        DashboardItem("settings", "Settings", Icons.Default.Settings, Screen.Settings.route)
    )

    val visibleItems: StateFlow<List<DashboardItem>> = userPreferencesRepository.dashboardConfig
        .map { configJson ->
            if (configJson == "DEFAULT") {
                allItems
            } else {
                try {
                    val type = object : TypeToken<List<String>>() {}.type
                    val visibleIds: List<String> = Gson().fromJson(configJson, type)
                    // Filter and sort based on saved IDs.
                    // Note: This simple logic appends items found in config.
                    // If we want to support reordering, we should iterate over visibleIds and find the item.
                    visibleIds.mapNotNull { id -> allItems.find { it.id == id } }
                } catch (e: Exception) {
                    allItems
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), allItems)

    fun updateConfig(visibleIds: List<String>) {
        viewModelScope.launch {
            val json = Gson().toJson(visibleIds)
            userPreferencesRepository.setDashboardConfig(json)
        }
    }
}
