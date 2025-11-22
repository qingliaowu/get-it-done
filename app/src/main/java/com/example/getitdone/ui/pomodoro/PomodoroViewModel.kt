package com.example.getitdone.ui.pomodoro

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getitdone.data.entity.TaskEntity
import com.example.getitdone.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PomodoroViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val taskId: Int = checkNotNull(savedStateHandle["taskId"])
    private var timerJob: Job? = null

    private val _timeLeft = MutableStateFlow(25 * 60) // 25 minutes in seconds
    val timeLeft: StateFlow<Int> = _timeLeft

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning

    private val _task = MutableStateFlow<TaskEntity?>(null)
    val task: StateFlow<TaskEntity?> = _task

    init {
        viewModelScope.launch {
            // We need to find the task. Since repository returns a Flow list, we filter.
            // Ideally we'd have getTaskById in DAO. For now, we filter from the list.
            // This is inefficient for a real app but works for this scope.
            taskRepository.getAllTasks().collect { tasks ->
                _task.value = tasks.find { it.id == taskId }
            }
        }
    }

    fun toggleTimer() {
        if (_isRunning.value) {
            pauseTimer()
        } else {
            startTimer()
        }
    }

    private fun startTimer() {
        _isRunning.value = true
        timerJob = viewModelScope.launch {
            while (_timeLeft.value > 0) {
                delay(1000)
                _timeLeft.value -= 1
            }
            _isRunning.value = false
            onTimerFinished()
        }
    }

    private fun pauseTimer() {
        _isRunning.value = false
        timerJob?.cancel()
    }

    private fun onTimerFinished() {
        viewModelScope.launch {
            _task.value?.let { currentTask ->
                taskRepository.updateTask(currentTask.copy(pomodoroCount = currentTask.pomodoroCount + 1))
                // Reset timer
                _timeLeft.value = 25 * 60
            }
        }
    }

    fun resetTimer() {
        pauseTimer()
        _timeLeft.value = 25 * 60
    }
}
