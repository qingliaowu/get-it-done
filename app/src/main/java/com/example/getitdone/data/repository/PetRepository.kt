package com.example.getitdone.data.repository

import com.example.getitdone.data.dao.PetDao
import com.example.getitdone.data.entity.PetEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetRepository @Inject constructor(
    private val petDao: PetDao
) {
    fun getPetState(): Flow<PetEntity?> = petDao.getPetState()

    suspend fun insertPet(pet: PetEntity) = petDao.insertPet(pet)

    suspend fun updatePet(pet: PetEntity) = petDao.updatePet(pet)
}
