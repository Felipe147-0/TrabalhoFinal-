package br.edu.ifsp.dmo.trabalhofinal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EUserType

@Entity(tableName = "cv_user")
class User (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "user_type")
    var userType: EUserType,

    @ColumnInfo(name = "name")
    var name: String,
) {
}