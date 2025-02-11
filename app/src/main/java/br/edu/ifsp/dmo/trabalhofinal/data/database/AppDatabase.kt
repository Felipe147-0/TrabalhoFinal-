package br.edu.ifsp.dmo.trabalhofinal.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.ifsp.dmo.trabalhofinal.data.dao.AddressDao
import br.edu.ifsp.dmo.trabalhofinal.data.dao.AddressUserDao
import br.edu.ifsp.dmo.trabalhofinal.data.dao.ClientDao
import br.edu.ifsp.dmo.trabalhofinal.data.dao.PlantDao
import br.edu.ifsp.dmo.trabalhofinal.data.dao.PlantUserDao
import br.edu.ifsp.dmo.trabalhofinal.data.dao.SupplierDao
import br.edu.ifsp.dmo.trabalhofinal.data.dao.UserDao
import br.edu.ifsp.dmo.trabalhofinal.data.model.Address
import br.edu.ifsp.dmo.trabalhofinal.data.model.AddressUser
import br.edu.ifsp.dmo.trabalhofinal.data.model.Client
import br.edu.ifsp.dmo.trabalhofinal.data.model.Plant
import br.edu.ifsp.dmo.trabalhofinal.data.model.PlantUser
import br.edu.ifsp.dmo.trabalhofinal.data.model.Supplier
import br.edu.ifsp.dmo.trabalhofinal.data.model.User


@Database(
    entities = [
        Address::class,
        AddressUser::class,
        Client::class,
        Plant::class,
        PlantUser::class,
        Supplier::class,
        User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        const val DATABASE_NAME = "cv_database.db"
        private lateinit var instance: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            if (!::instance.isInitialized) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                        .build()
                }
            }
            return instance
        }
    }

    abstract fun getAddressDao(): AddressDao
    abstract fun getAddressUserDao(): AddressUserDao
    abstract fun getClientDao(): ClientDao
    abstract fun getPlantDao(): PlantDao
    abstract fun getPlantUserDao(): PlantUserDao
    abstract fun getSupplierDao(): SupplierDao
    abstract fun getUserDao(): UserDao

}