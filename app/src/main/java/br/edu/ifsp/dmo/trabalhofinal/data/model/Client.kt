package br.edu.ifsp.dmo.trabalhofinal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
class Client (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long
) {
}