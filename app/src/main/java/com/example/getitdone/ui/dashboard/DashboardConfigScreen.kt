package com.example.getitdone.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardConfigScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    // We need all items to allow enabling/disabling
    // For simplicity, we'll hardcode the list of all possible IDs here or expose it from ViewModel
    // Let's assume the ViewModel exposes 'allItems' or we just know them.
    // To make it clean, let's add 'allItems' to ViewModel public API or just use the same list.
    
    val allItems = listOf(
        DashboardItem("todo", "To-Do List", Icons.Default.List, ""),
        DashboardItem("habit", "Habit Tracker", Icons.Default.CheckCircle, ""),
        DashboardItem("pet", "My Pet", Icons.Default.Face, ""),
        DashboardItem("calendar", "Calendar", Icons.Default.DateRange, ""),
        DashboardItem("mood", "Mood Tracker", Icons.Default.Star, ""),
        DashboardItem("motivation", "Motivation", Icons.Default.Favorite, ""),
        DashboardItem("settings", "Settings", Icons.Default.Settings, "")
    )

    val visibleItems by viewModel.visibleItems.collectAsState()
    val visibleIds = visibleItems.map { it.id }.toSet()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Customize Dashboard") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(allItems) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = item.title, style = MaterialTheme.typography.bodyLarge)
                    Switch(
                        checked = visibleIds.contains(item.id),
                        onCheckedChange = { isChecked ->
                            val newIds = if (isChecked) {
                                visibleIds + item.id
                            } else {
                                visibleIds - item.id
                            }
                            // Maintain order based on allItems for simplicity, or append if new
                            // A simple way is to filter allItems by newIds
                            val orderedIds = allItems.filter { it.id in newIds }.map { it.id }
                            viewModel.updateConfig(orderedIds)
                        }
                    )
                }
            }
        }
    }
}
