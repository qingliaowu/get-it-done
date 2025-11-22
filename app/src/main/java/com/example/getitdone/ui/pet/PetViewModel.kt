package com.example.getitdone.ui.pet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getitdone.data.entity.PetEntity
import com.example.getitdone.data.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    private val petRepository: PetRepository
) : ViewModel() {

    val petState: StateFlow<PetEntity?> = petRepository.getPetState()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    init {
        viewModelScope.launch {
            // Initialize pet if not exists
            // In a real app, we might check if it's null first in a more robust way or use a separate initialization flow
            // For now, we rely on the UI to trigger creation or handle null
        }
    }

    fun createPet(name: String) {
        viewModelScope.launch {
            petRepository.insertPet(
                PetEntity(
                    id = 0,
                    name = name,
                    hunger = 50,
                    happiness = 50,
                    experience = 0,
                    level = 1
                )
            )
        }
    }

    fun feedPet() {
        val currentPet = petState.value ?: return
        viewModelScope.launch {
            val newHunger = (currentPet.hunger + 10).coerceAtMost(100)
            val newExp = currentPet.experience + 5
            petRepository.updatePet(currentPet.copy(hunger = newHunger, experience = newExp))
        }
    }

    fun playWithPet() {
        val currentPet = petState.value ?: return
        viewModelScope.launch {
            val newHappiness = (currentPet.happiness + 10).coerceAtMost(100)
            val newHunger = (currentPet.hunger - 5).coerceAtLeast(0)
            val newExp = currentPet.experience + 10
            petRepository.updatePet(currentPet.copy(happiness = newHappiness, hunger = newHunger, experience = newExp))
        }
    }
}
