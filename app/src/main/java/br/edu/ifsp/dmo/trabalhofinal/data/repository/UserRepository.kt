package br.edu.ifsp.dmo.trabalhofinal.data.repository

import android.content.Context
import br.edu.ifsp.dmo.trabalhofinal.data.database.AppDatabase
import br.edu.ifsp.dmo.trabalhofinal.data.model.User

class UserRepository(context: Context) {

    private val database = AppDatabase.getInstance(context)
    private val dao = database.getUserDao()

    suspend fun findByEmail(email: String): User? {
        return dao.selectByEmail(email)
    }

    suspend fun findById(id: Long): User {
        return dao.selectById(id)
    }

    suspend fun insert(user: User): Long {
        return dao.insert(user)
    }
}