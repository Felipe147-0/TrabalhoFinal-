package br.edu.ifsp.dmo.trabalhofinal.ui.stock

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.dmo.trabalhofinal.data.database.AppDatabase
import br.edu.ifsp.dmo.trabalhofinal.data.model.Plant
import br.edu.ifsp.dmo.trabalhofinal.data.model.PlantUser
import br.edu.ifsp.dmo.trabalhofinal.data.repository.PlantRepository
import br.edu.ifsp.dmo.trabalhofinal.data.repository.PlantUserRepository
import kotlinx.coroutines.launch

class StockViewModel(application: Application) : AndroidViewModel(application) {
    private val plantRepository: PlantRepository
    private val plantUserRepository: PlantUserRepository

    private val _plantUsers = MutableLiveData<Pair<List<PlantUser>, Map<Long, Plant>>>()
    val plantUsers: LiveData<Pair<List<PlantUser>, Map<Long, Plant>>> = _plantUsers

    init {
        val plantDao = AppDatabase.getInstance(application.applicationContext).getPlantDao()
        plantRepository = PlantRepository(plantDao)
        plantUserRepository = PlantUserRepository(application.applicationContext)
    }

    fun loadStock(userId: Long) {
        viewModelScope.launch {
            val plantUsersList = plantUserRepository.getPlantsByUser(userId)
            val plantMap = plantRepository.getAllPlants().associateBy { it.id }
            _plantUsers.postValue(plantUsersList to plantMap)
        }
    }
}
