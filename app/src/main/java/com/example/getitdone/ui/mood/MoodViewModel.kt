package com.example.getitdone.ui.mood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getitdone.data.entity.MoodEntity
import com.example.getitdone.data.dao.MoodDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoodViewModel @Inject constructor(
    private val moodDao: MoodDao
) : ViewModel() {

    val moodLogs: StateFlow<List<MoodEntity>> = moodDao.getAllMoodLogs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun logMood(level: Int, note: String?) {
        viewModelScope.launch {
            moodDao.insertMood(
                MoodEntity(
                    timestamp = System.currentTimeMillis(),
                    moodLevel = level,
                    note = note
                )
            )
        }
    }
}
