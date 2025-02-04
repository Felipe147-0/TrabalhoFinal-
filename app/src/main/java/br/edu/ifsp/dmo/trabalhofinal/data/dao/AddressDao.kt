package br.edu.ifsp.dmo.trabalhofinal.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.dmo.trabalhofinal.data.model.Address

@Dao
interface AddressDao {

    @Insert
    suspend fun insert(address: Address): Long

    @Update
    suspend fun update(address: Address): Int

    @Delete
    suspend fun delete(address: Address): Int

    @Query("SELECT * FROM address")
    suspend fun selectAll(): List<Address>

    @Query("SELECT * FROM address WHERE id = :id LIMIT 1")
    suspend fun selectById(id: Long): Address

}