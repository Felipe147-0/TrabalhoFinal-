package br.edu.ifsp.dmo.trabalhofinal.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.dmo.trabalhofinal.data.model.Plant

@Dao
interface PlantDao {

    @Insert
    suspend fun insert(plant: Plant): Long

    @Update
    suspend fun update(plant: Plant): Int

    @Delete
    suspend fun delete(plant: Plant): Int

    @Query("SELECT * FROM plant")
    suspend fun selectAll(): List<Plant>

    @Query("SELECT * FROM plant WHERE id = :id LIMIT 1")
    suspend fun selectById(id: Long): Plant

}