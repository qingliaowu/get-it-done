package com.example.getitdone.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val backupStatus by viewModel.backupStatus.collectAsState()
    val exportStatus by viewModel.exportStatus.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Data Management", style = MaterialTheme.typography.titleLarge)
            
            Button(
                onClick = { viewModel.performCloudBackup() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Backup to Cloud")
            }
            if (backupStatus != null) {
                Text(text = backupStatus!!, color = MaterialTheme.colorScheme.primary)
            }

            Button(
                onClick = { viewModel.exportData() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Export Data (JSON)")
            }
            if (exportStatus != null) {
                Text(text = exportStatus!!, color = MaterialTheme.colorScheme.primary)
            }

            Divider()

            Text("Categories", style = MaterialTheme.typography.titleLarge)
            Text("Category management coming soon...", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
