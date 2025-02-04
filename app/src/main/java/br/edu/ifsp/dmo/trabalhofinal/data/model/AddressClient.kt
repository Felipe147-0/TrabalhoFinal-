package br.edu.ifsp.dmo.trabalhofinal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "address_client",
    primaryKeys = ["id_user" , "id_address"],
    foreignKeys = [
        ForeignKey(
            entity = Address::class,
            parentColumns = ["id"],
            childColumns = ["id_address"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Client::class,
            parentColumns = ["id"],
            childColumns = ["id_client"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class AddressClient(

    @ColumnInfo(name = "id_address")
    var idAddress: Long,

    @ColumnInfo(name = "id_client")
    var idClient: Long

) {
}