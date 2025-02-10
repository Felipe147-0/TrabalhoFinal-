package br.edu.ifsp.dmo.trabalhofinal.ui.plant

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.dmo.trabalhofinal.data.database.AppDatabase
import br.edu.ifsp.dmo.trabalhofinal.data.model.Plant
import br.edu.ifsp.dmo.trabalhofinal.data.repository.PlantRepository
import kotlinx.coroutines.launch

class PlantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PlantRepository

    private val _plants = MutableLiveData<List<Plant>>()
    val plants: LiveData<List<Plant>> get() = _plants

    init {
        val plantDao = AppDatabase.getInstance(application).getPlantDao()
        repository = PlantRepository(plantDao)
    }

    fun insertPlant(plant: Plant) {
        viewModelScope.launch {
            repository.insertPlant(plant)
            loadPlants()
        }
    }

    fun updatePlant(plant: Plant) {
        viewModelScope.launch {
            repository.updatePlant(plant)
            loadPlants()
        }
    }

    fun deletePlant(plant: Plant) {
        viewModelScope.launch {
            repository.deletePlant(plant)
            loadPlants()
        }
    }

    fun loadPlants() {
        viewModelScope.launch {
            _plants.postValue(repository.getAllPlants())
        }
    }

    fun getPlantById(id: Long): LiveData<Plant?> {
        val plantLiveData = MutableLiveData<Plant?>()
        viewModelScope.launch {
            plantLiveData.postValue(repository.getPlantById(id))
        }
        return plantLiveData
    }
}
