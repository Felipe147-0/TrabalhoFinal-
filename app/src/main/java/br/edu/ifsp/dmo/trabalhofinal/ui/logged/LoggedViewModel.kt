package br.edu.ifsp.dmo.trabalhofinal.ui.logged

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.dmo.trabalhofinal.data.repository.DataStoreRepository
import kotlinx.coroutines.launch

class LoggedViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStoreRepository = DataStoreRepository(application)

    private val _loggedOut = MutableLiveData<Boolean>()
    val loggedOut : LiveData<Boolean> = _loggedOut

    fun logout(){
        viewModelScope.launch {
            dataStoreRepository.logout()
            _loggedOut.value = true
        }
    }
}