package br.edu.ifsp.dmo.trabalhofinal.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.dmo.trabalhofinal.data.model.Client

@Dao
interface ClientDao {

    @Insert
    suspend fun insert(client: Client): Long

    @Update
    suspend fun update(client: Client): Int

    @Delete
    suspend fun delete(client: Client): Int

    @Query("SELECT * FROM client")
    suspend fun selectAll(): List<Client>

    @Query("SELECT * FROM client WHERE id = :id LIMIT 1")
    suspend fun selectById(id: Long): Client

}