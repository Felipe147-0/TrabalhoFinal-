package br.edu.ifsp.dmo.trabalhofinal.ui.logged

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.dmo.trabalhofinal.data.model.PlantUser
import br.edu.ifsp.dmo.trabalhofinal.data.repository.DataStoreRepository
import br.edu.ifsp.dmo.trabalhofinal.data.repository.PlantUserRepository
import kotlinx.coroutines.launch

class LoggedViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStoreRepository = DataStoreRepository(application)
    private val plantUserRepository = PlantUserRepository(application)

    private val _loggedOut = MutableLiveData<Boolean>()
    val loggedOut : LiveData<Boolean> = _loggedOut

    private val _inserted = MutableLiveData<Boolean>()
    val inserted: LiveData<Boolean> = _inserted

    fun logout(){
        viewModelScope.launch {
            dataStoreRepository.logout()
            _loggedOut.value = true
        }
    }

    fun registerNewPlantUser(userId: String, plantId: Long, quantity: Int) {
        val plantUser = PlantUser(plantId,userId.toLong(),quantity)
        viewModelScope.launch {
            _inserted.value = plantUserRepository.insert(plantUser)
        }
    }
}