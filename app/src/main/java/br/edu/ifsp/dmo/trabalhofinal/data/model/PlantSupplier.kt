package br.edu.ifsp.dmo.trabalhofinal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "plant_supplier",
    primaryKeys = ["id_plant" , "id_supplier"],
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["id"],
            childColumns = ["id_plant"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Supplier::class,
            parentColumns = ["id"],
            childColumns = ["id_supplier"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class PlantSupplier (

    @ColumnInfo(name = "id_plant")
    var idPlant: Long,

    @ColumnInfo(name = "id_supplier")
    var idSupplier: Long
) {

}