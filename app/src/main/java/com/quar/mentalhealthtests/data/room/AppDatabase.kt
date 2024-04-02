package com.quar.mentalhealthtests.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.quar.mentalhealthtests.data.DBNAME
import com.quar.mentalhealthtests.data.room.table.Category
import com.quar.mentalhealthtests.data.room.table.Quiz
import com.quar.mentalhealthtests.data.room.table.Rayting

@Database(
    entities = [Category::class, Quiz::class, Rayting::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        @Synchronized
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DBNAME
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .createFromAsset(DBNAME)
                .build()
    }
}



