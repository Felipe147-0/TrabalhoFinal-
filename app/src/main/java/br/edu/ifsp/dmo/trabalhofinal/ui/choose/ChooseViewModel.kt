package br.edu.ifsp.dmo.trabalhofinal.ui.choose


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.dmo.trabalhofinal.data.database.AppDatabase
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EPlantSize
import br.edu.ifsp.dmo.trabalhofinal.data.model.Plant
import br.edu.ifsp.dmo.trabalhofinal.data.repository.PlantRepository
import kotlinx.coroutines.launch

class ChooseViewModel(application: Application)   : AndroidViewModel(application) {

    private val repository: PlantRepository

    init {
        val plantDao = AppDatabase.getInstance(application).getPlantDao()
        repository = PlantRepository(plantDao)
    }

    private val _filteredPlants = MutableLiveData<List<Plant>>()
    val filteredPlants: LiveData<List<Plant>> = _filteredPlants



    fun fetchFilteredPlants(size: EPlantSize, isFrutiferous: Boolean){
        viewModelScope.launch {
            _filteredPlants.value = repository.getPlantsByFilters(size,isFrutiferous)
        }
    }
}