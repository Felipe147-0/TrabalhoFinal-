package br.edu.ifsp.dmo.trabalhofinal.ui.stock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.dmo.trabalhofinal.data.model.Plant
import br.edu.ifsp.dmo.trabalhofinal.data.model.PlantUser
import br.edu.ifsp.dmo.trabalhofinal.data.repository.PlantRepository
import br.edu.ifsp.dmo.trabalhofinal.data.repository.PlantUserRepository
import kotlinx.coroutines.launch

class StockViewModel(
    private val plantRepository: PlantRepository,
    private val plantUserRepository: PlantUserRepository
) : ViewModel() {

    private val _plantUsers = MutableLiveData<Pair<List<PlantUser>, Map<Long, Plant>>>()
    val plantUsers: LiveData<Pair<List<PlantUser>, Map<Long, Plant>>> = _plantUsers

    fun loadStock(userId: Long) {
        viewModelScope.launch {
            val plantUsersList = plantUserRepository.getPlantsByUser(userId)
            val plantMap = plantRepository.getAllPlants().associateBy { it.id }
            _plantUsers.postValue(plantUsersList to plantMap)
        }
    }
}
