package br.edu.ifsp.dmo.trabalhofinal.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.dmo.trabalhofinal.data.model.PlantUser

@Dao
interface PlantUserDao {

    @Insert
    suspend fun insert(plantUser: PlantUser): Long

    @Update
    suspend fun update(plantUser: PlantUser): Int

    @Delete
    suspend fun delete(plantUser: PlantUser): Int

    @Query("SELECT * FROM plant_user")
    suspend fun selectAll(): List<PlantUser>

    @Query("SELECT * FROM plant_user WHERE id_plant = :id")
    suspend fun selectByIdPlant(id: Long): List<PlantUser>

    @Query("SELECT * FROM plant_user WHERE id_user = :id")
    suspend fun selectByIdSupplier(id: Long): List<PlantUser>

    @Query("SELECT * FROM plant_user WHERE id_plant = :idAddress AND id_user = :idClient LIMIT 1")
    suspend fun selectByIdPlantAndIdSupplier(idAddress: Long, idClient: Long): PlantUser

}