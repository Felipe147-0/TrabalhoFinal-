package br.edu.ifsp.dmo.trabalhofinal.data.model

import android.location.Address
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Dictionary

@Entity(tableName = "supplier")
class Supplier (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long,

    var addresses : List<Address>,
    var plantCatalogue : Dictionary<Plant, Int>
) {
}