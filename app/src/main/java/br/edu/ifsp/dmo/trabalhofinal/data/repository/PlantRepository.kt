package br.edu.ifsp.dmo.trabalhofinal.data.repository

import br.edu.ifsp.dmo.trabalhofinal.data.dao.PlantDao
import br.edu.ifsp.dmo.trabalhofinal.data.model.Plant

class PlantRepository(private val plantDao: PlantDao) {

    suspend fun insertPlant(plant: Plant): Long {
        return plantDao.insert(plant)
    }

    suspend fun updatePlant(plant: Plant): Int {
        return plantDao.update(plant)
    }

    suspend fun deletePlant(plant: Plant): Int {
        return plantDao.delete(plant)
    }

    suspend fun getAllPlants(): List<Plant> {
        return plantDao.selectAll()
    }

    suspend fun getPlantById(id: Long): Plant? {
        return plantDao.selectById(id)
    }
}
