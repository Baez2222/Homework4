package com.example.android.homework4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [Dream::class], version = 1, exportSchema = false)
public abstract class DreamRoomDatabase: RoomDatabase(){
    // connects with DAO
    abstract fun dreamDAO():DreamDAO // getter

    companion object{

        @Volatile // singleton
        private var INSTANCE:DreamRoomDatabase? = null

        fun getDatabase(context : Context): DreamRoomDatabase{

            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DreamRoomDatabase::class.java,
                    "dream_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}