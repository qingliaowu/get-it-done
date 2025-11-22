package com.example.getitdone.ui.mood

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodScreen(
    navController: NavController,
    viewModel: MoodViewModel = hiltViewModel()
) {
    val moodLogs by viewModel.moodLogs.collectAsState()
    var showLogDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Emotion Tracker") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showLogDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Log Mood")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(moodLogs) { log ->
                MoodItem(log)
            }
        }

        if (showLogDialog) {
            LogMoodDialog(
                onDismiss = { showLogDialog = false },
                onConfirm = { level, note ->
                    viewModel.logMood(level, note)
                    showLogDialog = false
                }
            )
        }
    }
}

@Composable
fun MoodItem(log: com.example.getitdone.data.entity.MoodEntity) {
    val dateFormat = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Mood: ${log.moodLevel}/5",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = dateFormat.format(Date(log.timestamp)),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            if (!log.note.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = log.note, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun LogMoodDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int, String) -> Unit
) {
    var moodLevel by remember { mutableStateOf(3f) }
    var note by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("How are you feeling?") },
        text = {
            Column {
                Text("Level: ${moodLevel.toInt()}")
                Slider(
                    value = moodLevel,
                    onValueChange = { moodLevel = it },
                    valueRange = 1f..5f,
                    steps = 3
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Note (Optional)") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(moodLevel.toInt(), note) }) {
                Text("Log")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
