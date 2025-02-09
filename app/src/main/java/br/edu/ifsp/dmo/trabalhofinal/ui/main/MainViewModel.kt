package br.edu.ifsp.dmo.trabalhofinal.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EUF
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EUserType
import br.edu.ifsp.dmo.trabalhofinal.data.model.Address
import br.edu.ifsp.dmo.trabalhofinal.data.model.AddressUser
import br.edu.ifsp.dmo.trabalhofinal.data.model.Client
import br.edu.ifsp.dmo.trabalhofinal.data.model.Supplier
import br.edu.ifsp.dmo.trabalhofinal.data.model.User
import br.edu.ifsp.dmo.trabalhofinal.data.repository.AddressRepository
import br.edu.ifsp.dmo.trabalhofinal.data.repository.AddressUserRepository
import br.edu.ifsp.dmo.trabalhofinal.data.repository.ClientRepository
import br.edu.ifsp.dmo.trabalhofinal.data.repository.DataStoreRepository
import br.edu.ifsp.dmo.trabalhofinal.data.repository.SupplierRepository
import br.edu.ifsp.dmo.trabalhofinal.data.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)
    private val dataStoreRepository = DataStoreRepository(application)
    private val addressRepository = AddressRepository(application)
    private val addressUserRepository = AddressUserRepository(application)
    private val clientRepository = ClientRepository(application)
    private val supplierRepository = SupplierRepository(application)

    private val _loggedIn = MutableLiveData<Long>()
    val loggedIn : LiveData<Long> = _loggedIn

    private val _userLogged = MutableLiveData<User>()
    val userLogged : LiveData<User> = _userLogged

    private val _sucessfulInsert = MutableLiveData<Boolean>()
    val successfulInsert : LiveData<Boolean> = _sucessfulInsert

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

    fun registerNewUser(tipo: EUserType, name: String?, street: String?, district: String?, city: String?, email: String?, password: String?, state: EUF) {
        val user = User(email=email!!, password = password!!, name = name!!, userType = tipo)
        val address = Address(uf = state, city = city!!, district = district!!, street = street!!)
        viewModelScope.launch {
            val userId = userRepository.insert(user)
            if(userId > 0){
                val typedInsert : Boolean = if(tipo==EUserType.CLIENT){
                    val client = Client(idUser = userId)
                    clientRepository.insert(client)
                }else{
                    val supplier = Supplier(idUser = userId)
                    supplierRepository.insert(supplier)
                }
                val addressInsert = addressRepository.insert(address)
                if(typedInsert && addressInsert > 0){
                    val addressUser = AddressUser(idAddress = addressInsert, idUser = userId)
                    _sucessfulInsert.value = addressUserRepository.insert(addressUser)
                }else{
                    _sucessfulInsert.value = false
                }
            }else{
                _sucessfulInsert.value = false
            }
        }
    }
}