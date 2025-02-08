package br.edu.ifsp.dmo.trabalhofinal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "plant_user",
    primaryKeys = ["id_plant" , "id_user"],
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["id"],
            childColumns = ["id_plant"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["id_user"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class PlantUser (

    @ColumnInfo(name = "id_plant")
    var idPlant: Long,

    @ColumnInfo(name = "id_user")
    var idUser: Long
) {

}