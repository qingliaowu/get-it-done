package com.example.getitdone.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getitdone.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val currentTheme: StateFlow<String> = userPreferencesRepository.theme
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "SYSTEM")

    private val _backupStatus = MutableStateFlow<String?>(null)
    val backupStatus: StateFlow<String?> = _backupStatus

    private val _exportStatus = MutableStateFlow<String?>(null)
    val exportStatus: StateFlow<String?> = _exportStatus

    fun setTheme(theme: String) {
        viewModelScope.launch {
            userPreferencesRepository.setTheme(theme)
        }
    }

    fun performCloudBackup() {
        viewModelScope.launch {
            _backupStatus.value = "Backing up..."
            delay(2000) // Simulate network delay
            _backupStatus.value = "Backup Successful to Google Drive!"
            delay(3000)
            _backupStatus.value = null
        }
    }

    fun exportData() {
        viewModelScope.launch {
            _exportStatus.value = "Exporting data..."
            delay(1500) // Simulate file writing
            _exportStatus.value = "Data exported to Downloads/getitdone_backup.json"
            delay(3000)
            _exportStatus.value = null
        }
    }
}
