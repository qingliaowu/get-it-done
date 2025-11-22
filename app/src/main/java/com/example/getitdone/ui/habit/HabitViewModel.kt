package com.example.getitdone.ui.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getitdone.data.entity.HabitEntity
import com.example.getitdone.data.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val habitRepository: HabitRepository
) : ViewModel() {

    val habits: StateFlow<List<HabitEntity>> = habitRepository.getAllHabits()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addHabit(title: String, frequency: String) {
        viewModelScope.launch {
            habitRepository.insertHabit(
                HabitEntity(
                    title = title,
                    frequency = frequency
                )
            )
        }
    }

    fun incrementStreak(habit: HabitEntity) {
        viewModelScope.launch {
            habitRepository.updateHabit(habit.copy(streak = habit.streak + 1))
        }
    }

    fun deleteHabit(habit: HabitEntity) {
        viewModelScope.launch {
            habitRepository.deleteHabit(habit)
        }
    }
}
