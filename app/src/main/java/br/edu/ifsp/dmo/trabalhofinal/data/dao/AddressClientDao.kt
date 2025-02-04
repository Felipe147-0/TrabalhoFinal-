package br.edu.ifsp.dmo.trabalhofinal.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.dmo.trabalhofinal.data.model.AddressClient

@Dao
interface AddressClientDao {

    @Insert
    suspend fun insert(addressClient: AddressClient): Long

    @Update
    suspend fun update(addressClient: AddressClient): Int

    @Delete
    suspend fun delete(addressClient: AddressClient): Int

    @Query("SELECT * FROM address_client")
    suspend fun selectAll(): List<AddressClient>

    @Query("SELECT * FROM address_client WHERE id_address = :id")
    suspend fun selectByIdAddress(id: Long): List<AddressClient>

    @Query("SELECT * FROM address_client WHERE id_client = :id")
    suspend fun selectByIdClient(id: Long): List<AddressClient>

    @Query("SELECT * FROM address_client WHERE id_address = :idAddress AND id_client = :idClient LIMIT 1")
    suspend fun selectByIdAddressAndIdClient(idAddress: Long, idClient: Long): AddressClient

}