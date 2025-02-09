package br.edu.ifsp.dmo.trabalhofinal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EPlantSize

@Entity(tableName = "plant")
class Plant (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long = 0,

    @ColumnInfo(name = "species")
    var species : String,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "size")
    var size : EPlantSize,

    /*@ColumnInfo(name = "native")
    var native: Boolean,*/

    @ColumnInfo(name = "frutiferous")
    var frutiferous: Boolean
) {
}