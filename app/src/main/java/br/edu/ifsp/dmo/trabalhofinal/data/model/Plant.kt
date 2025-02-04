package br.edu.ifsp.dmo.trabalhofinal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EPlantSize

@Entity(tableName = "plant")
class Plant (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long,
    var species : String,
    var name : String,
    var size : EPlantSize,
    var native: Boolean,
    var frutiferous: Boolean
) {
}