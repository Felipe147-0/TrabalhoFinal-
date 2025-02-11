package br.edu.ifsp.dmo.trabalhofinal.data.repository

import android.content.Context
import br.edu.ifsp.dmo.trabalhofinal.data.database.AppDatabase
import br.edu.ifsp.dmo.trabalhofinal.data.model.Supplier

class SupplierRepository(context: Context) {
    private val database = AppDatabase.getInstance(context)
    private val dao = database.getSupplierDao()

    suspend fun insert(supplier: Supplier): Boolean {
        return dao.insert(supplier) > 0
    }
}