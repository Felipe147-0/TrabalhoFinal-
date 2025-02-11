package br.edu.ifsp.dmo.trabalhofinal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EUF

@Entity(tableName = "address")
class Address(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "uf")
    var uf: EUF,

    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "district")
    var district: String,

    @ColumnInfo(name = "street")
    var street: String

) {

}
