package br.edu.ifsp.dmo.trabalhofinal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EUF

@Entity(tableName = "address")
class Adress (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long,

    var uf: EUF,

    var city : String,

    var district : String,

    var street : String

){

}
