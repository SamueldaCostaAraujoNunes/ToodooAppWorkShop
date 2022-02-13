package com.samuelnunes.too_dooapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.samuelnunes.too_dooapp.common.Constants.DATABASE_TODO_TABLE
import com.samuelnunes.too_dooapp.data.local.converters.DateTimeLocalConverter
import com.samuelnunes.too_dooapp.domain.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = true)
@TypeConverters(DateTimeLocalConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

class AppDatabaseFactory(){
    companion object {
        fun getInstance(context: Context): AppDatabase{
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_TODO_TABLE)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}