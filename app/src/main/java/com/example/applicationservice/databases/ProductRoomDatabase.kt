package com.example.applicationservice.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.applicationservice.dao.ProductDao
import com.example.applicationservice.models.Product

@Database(entities = [Product::class], version = 1, exportSchema =  false)
abstract class ProductRoomDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
    companion object{
        @Volatile
        private var INSTANCE :  ProductRoomDatabase ?= null
        fun getDatabase(context: Context): ProductRoomDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext ,
                    ProductRoomDatabase :: class.java ,
                    "product_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}