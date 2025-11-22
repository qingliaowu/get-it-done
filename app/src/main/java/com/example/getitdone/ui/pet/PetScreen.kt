package com.example.getitdone.ui.pet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetScreen(
    navController: NavController,
    viewModel: PetViewModel = hiltViewModel()
) {
    val petState by viewModel.petState.collectAsState()
    var showNameDialog by remember { mutableStateOf(false) }

    // Check if pet exists, if not show dialog or empty state
    LaunchedEffect(petState) {
        if (petState == null) {
            // In a real app, we might want to delay this or check a "loading" state
            // For simplicity, we'll assume if it's null after a moment, we need to create one.
            // But since stateIn initial value is null, we need to be careful.
            // Let's just show a "Create Pet" button if null.
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Digital Pet") })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (petState == null) {
                Button(onClick = { showNameDialog = true }) {
                    Text("Adopt a Pet")
                }
            } else {
                petState?.let { pet ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = pet.name, style = MaterialTheme.typography.headlineLarge)
                        Text(text = "Level: ${pet.level}", style = MaterialTheme.typography.titleMedium)
                        
                        // Stats
                        StatBar(label = "Hunger", value = pet.hunger / 100f)
                        StatBar(label = "Happiness", value = pet.happiness / 100f)
                        StatBar(label = "Experience", value = (pet.experience % 100) / 100f) // Simplified level up logic

                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Button(onClick = { viewModel.feedPet() }) {
                                Text("Feed")
                            }
                            Button(onClick = { viewModel.playWithPet() }) {
                                Text("Play")
                            }
                        }
                    }
                }
            }
        }

        if (showNameDialog) {
            var name by remember { mutableStateOf("") }
            AlertDialog(
                onDismissRequest = { showNameDialog = false },
                title = { Text("Name Your Pet") },
                text = {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Pet Name") }
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.createPet(name)
                        showNameDialog = false
                    }) {
                        Text("Adopt")
                    }
                }
            )
        }
    }
}

@Composable
fun StatBar(label: String, value: Float) {
    Column(modifier = Modifier.width(200.dp)) {
        Text(text = label)
        LinearProgressIndicator(
            progress = value,
            modifier = Modifier.fillMaxWidth().height(8.dp)
        )
    }
}
