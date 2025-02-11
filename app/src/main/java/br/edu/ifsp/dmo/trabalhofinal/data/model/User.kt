package br.edu.ifsp.dmo.trabalhofinal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EUserType
import java.io.Serializable

@Entity(
    tableName = "cv_user",
    indices = [Index(value = ["email"], unique = true)]
)
class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "user_type")
    var userType: EUserType,

    @ColumnInfo(name = "name")
    var name: String,
) : Serializable {
    companion object {
        fun authenticate(user: User, email: String, password: String): Boolean {
            return (user.email == email && user.password == password)
        }
    }
}