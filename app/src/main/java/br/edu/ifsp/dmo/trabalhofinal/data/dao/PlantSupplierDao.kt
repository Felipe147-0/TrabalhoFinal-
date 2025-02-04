package br.edu.ifsp.dmo.trabalhofinal.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.dmo.trabalhofinal.data.model.PlantSupplier

@Dao
interface PlantSupplierDao {

    @Insert
    suspend fun insert(plantSupplier: PlantSupplier): Long

    @Update
    suspend fun update(plantSupplier: PlantSupplier): Int

    @Delete
    suspend fun delete(plantSupplier: PlantSupplier): Int

    @Query("SELECT * FROM plant_supplier")
    suspend fun selectAll(): List<PlantSupplier>

    @Query("SELECT * FROM plant_supplier WHERE id_plant = :id")
    suspend fun selectByIdPlant(id: Long): List<PlantSupplier>

    @Query("SELECT * FROM plant_supplier WHERE id_supplier = :id")
    suspend fun selectByIdSupplier(id: Long): List<PlantSupplier>

    @Query("SELECT * FROM plant_supplier WHERE id_plant = :idAddress AND id_supplier = :idClient LIMIT 1")
    suspend fun selectByIdPlantAndIdSupplier(idAddress: Long, idClient: Long): PlantSupplier

}