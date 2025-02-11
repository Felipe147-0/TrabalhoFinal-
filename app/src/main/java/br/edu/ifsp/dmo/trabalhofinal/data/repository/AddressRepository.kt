package br.edu.ifsp.dmo.trabalhofinal.data.repository

import android.content.Context
import br.edu.ifsp.dmo.trabalhofinal.data.database.AppDatabase
import br.edu.ifsp.dmo.trabalhofinal.data.model.Address

class AddressRepository(context: Context) {
    private val database = AppDatabase.getInstance(context)
    private val dao = database.getAddressDao()

    suspend fun insert(address: Address): Long {
        return dao.insert(address)
    }
}