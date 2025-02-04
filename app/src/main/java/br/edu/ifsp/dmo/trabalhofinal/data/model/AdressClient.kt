package br.edu.ifsp.dmo.trabalhofinal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "address_client", primaryKeys = ["id_user" , "id_address"])
class AdressClient(

    @ColumnInfo(name = "id_address")
    var idAddress: Long,

    @ColumnInfo(name = "id_client")
    var idClient: Long

) {
}