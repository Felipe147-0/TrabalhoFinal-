package br.edu.ifsp.dmo.trabalhofinal.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.dmo.trabalhofinal.data.model.User
import br.edu.ifsp.dmo.trabalhofinal.data.repository.DataStoreRepository
import br.edu.ifsp.dmo.trabalhofinal.data.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)
    private val dataStoreRepository = DataStoreRepository(application)

    private val _loggedIn = MutableLiveData<Long>()
    val loggedIn : LiveData<Long> = _loggedIn

    private val _userLogged = MutableLiveData<User>()
    val userLogged : LiveData<User> = _userLogged

    fun login(email:String, senha:String, saveLogin:Boolean, stayLoggedIn:Boolean){
        viewModelScope.launch {
            val user = userRepository.findByEmail(email)
            if(user!=null){
                if(User.authenticate(user,email,senha)){
                    _loggedIn.value = user.id
                    if(saveLogin || stayLoggedIn)
                        savePreferences(email,senha,saveLogin,stayLoggedIn)
                    else
                        savePreferences("","",saveLogin,stayLoggedIn)
                }else{
                    _loggedIn.value = 0L
                }
            }else{
                _loggedIn.value = 0L
            }
        }
    }

    private fun savePreferences(email: String, senha: String, saveLogin: Boolean, stayLoggedIn: Boolean) {
        viewModelScope.launch {
            dataStoreRepository.savePreferences(email,senha,saveLogin,stayLoggedIn)
        }
    }

    fun fetchUser(id: Long) {
        viewModelScope.launch {
            _userLogged.value = userRepository.findById(id)
        }
    }
}