package br.edu.ifsp.dmo.trabalhofinal.data.repository

import android.content.Context
import br.edu.ifsp.dmo.trabalhofinal.data.database.AppDatabase
import br.edu.ifsp.dmo.trabalhofinal.data.model.PlantUser

class PlantUserRepository(context: Context) {
    private val database = AppDatabase.getInstance(context)
    private val dao = database.getPlantUserDao()

    suspend fun insert(plantUser:PlantUser) : Boolean{
        return dao.insert(plantUser)>0
    }
}