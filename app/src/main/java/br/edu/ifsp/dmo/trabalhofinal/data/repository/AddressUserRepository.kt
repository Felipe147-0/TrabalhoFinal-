package br.edu.ifsp.dmo.trabalhofinal.data.repository

import android.content.Context
import br.edu.ifsp.dmo.trabalhofinal.data.database.AppDatabase
import br.edu.ifsp.dmo.trabalhofinal.data.model.AddressUser

class AddressUserRepository(context: Context) {
    private val database = AppDatabase.getInstance(context)
    private val dao = database.getAddressUserDao()

    suspend fun insert(addressUser: AddressUser) : Boolean{
        return dao.insert(addressUser) > 0
    }
}