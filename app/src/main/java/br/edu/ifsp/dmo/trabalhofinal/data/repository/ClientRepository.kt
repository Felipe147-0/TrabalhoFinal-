package br.edu.ifsp.dmo.trabalhofinal.data.repository

import android.content.Context
import br.edu.ifsp.dmo.trabalhofinal.data.database.AppDatabase
import br.edu.ifsp.dmo.trabalhofinal.data.model.Client

class ClientRepository(context: Context) {
    private val database = AppDatabase.getInstance(context)
    private val dao = database.getClientDao()

    suspend fun insert(client: Client) : Boolean{
        return dao.insert(client) > 0
    }
}