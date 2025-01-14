package com.example.listapaginada.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.listapaginada.data.datasource.local.dao.PokemonDao
import com.example.listapaginada.data.datasource.local.dao.RemoteKeysDao
import com.example.listapaginada.data.datasource.local.model.PokemonEntity
import com.example.listapaginada.data.datasource.local.model.RemoteKeysEntity

@Database(entities = [PokemonEntity::class, RemoteKeysEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null
        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}