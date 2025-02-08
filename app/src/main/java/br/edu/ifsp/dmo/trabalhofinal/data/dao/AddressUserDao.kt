package br.edu.ifsp.dmo.trabalhofinal.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.dmo.trabalhofinal.data.model.AddressUser

@Dao
interface AddressUserDao {

    @Insert
    suspend fun insert(addressUser: AddressUser): Long

    @Update
    suspend fun update(addressUser: AddressUser): Int

    @Delete
    suspend fun delete(addressUser: AddressUser): Int

    @Query("SELECT * FROM address_user")
    suspend fun selectAll(): List<AddressUser>

    @Query("SELECT * FROM address_user WHERE id_address = :id")
    suspend fun selectByIdAddress(id: Long): List<AddressUser>

    @Query("SELECT * FROM address_user WHERE id_user = :id")
    suspend fun selectByIdClient(id: Long): List<AddressUser>

    @Query("SELECT * FROM address_user WHERE id_address = :idAddress AND id_user = :idClient LIMIT 1")
    suspend fun selectByIdAddressAndIdClient(idAddress: Long, idClient: Long): AddressUser

}