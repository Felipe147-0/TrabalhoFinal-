package br.edu.ifsp.dmo.trabalhofinal.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.dmo.trabalhofinal.data.model.Supplier

@Dao
interface SupplierDao {

    @Insert
    suspend fun insert(supplier: Supplier): Long

    @Update
    suspend fun update(supplier: Supplier): Int

    @Delete
    suspend fun delete(supplier: Supplier): Int

    @Query("SELECT * FROM supplier")
    suspend fun selectAll(): List<Supplier>

    @Query("SELECT * FROM supplier WHERE id = :id LIMIT 1")
    suspend fun selectById(id: Long): Supplier

}