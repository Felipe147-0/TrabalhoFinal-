package br.edu.ifsp.dmo.trabalhofinal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "client",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["id_cv_user"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class Client (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long,

    @ColumnInfo(name = "id_cv_user")
    var idUser : Long
) {
}