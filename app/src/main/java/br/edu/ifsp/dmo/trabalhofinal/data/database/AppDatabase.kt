package br.edu.ifsp.dmo.trabalhofinal.data.database

import android.content.Context
import android.location.Address
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.edu.ifsp.dmo.trabalhofinal.data.dao.AddressClientDao
import br.edu.ifsp.dmo.trabalhofinal.data.dao.AddressDao
import br.edu.ifsp.dmo.trabalhofinal.data.dao.ClientDao
import br.edu.ifsp.dmo.trabalhofinal.data.dao.PlantDao
import br.edu.ifsp.dmo.trabalhofinal.data.dao.PlantSupplierDao
import br.edu.ifsp.dmo.trabalhofinal.data.dao.SupplierDao
import br.edu.ifsp.dmo.trabalhofinal.data.dao.UserDao
import br.edu.ifsp.dmo.trabalhofinal.data.model.AddressClient
import br.edu.ifsp.dmo.trabalhofinal.data.model.Client
import br.edu.ifsp.dmo.trabalhofinal.data.model.Plant
import br.edu.ifsp.dmo.trabalhofinal.data.model.PlantSupplier
import br.edu.ifsp.dmo.trabalhofinal.data.model.Supplier
import br.edu.ifsp.dmo.trabalhofinal.data.model.User


@TypeConverters(TypeConverters::class)
@Database(entities = [
    Address::class,
    AddressClient::class,
    Client::class,
    Plant::class,
    PlantSupplier::class,
    Supplier::class,
    User::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        const val DATABASE_NAME = "cv_database.db"
        private lateinit var instance: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            if (!::instance.isInitialized) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
                }
            }
            return instance
        }
    }

    abstract fun getAddressDao(): AddressDao
    abstract fun getAddressClientDao(): AddressClientDao
    abstract fun getClientDao(): ClientDao
    abstract fun getPlantDao(): PlantDao
    abstract fun getPlantSupplier(): PlantSupplierDao
    abstract fun getSupplier(): SupplierDao
    abstract fun getUser(): UserDao

}