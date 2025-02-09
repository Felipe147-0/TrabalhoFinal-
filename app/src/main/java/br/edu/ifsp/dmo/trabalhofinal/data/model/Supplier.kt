package br.edu.ifsp.dmo.trabalhofinal.data.model

import android.location.Address
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Dictionary

@Entity(tableName = "supplier",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["id_cv_user"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class Supplier (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long = 0,

    @ColumnInfo(name = "id_cv_user")
    var idUser : Long
) {
}