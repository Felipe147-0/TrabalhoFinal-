package br.edu.ifsp.dmo.trabalhofinal.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.dmo.trabalhofinal.data.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User): Int

    @Delete
    suspend fun delete(user: User): Int

    @Query("SELECT * FROM cv_user")
    suspend fun selectAll(): List<User>

    @Query("SELECT * FROM cv_user WHERE id = :id LIMIT 1")
    suspend fun selectById(id: Long): User

}