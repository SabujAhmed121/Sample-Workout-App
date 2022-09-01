package eu.tutorials.a7_minutesworkoutapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class],version = 1)
abstract class HistoryDatabase:RoomDatabase(){

    abstract fun historyDao():HistoryDao


    companion object {

        @Volatile
        private var INSTANCE: HistoryDatabase? = null


//         * @param context The application context Singleton, used to get access to the filesystem.
//         */
        fun getInstance(context: Context): HistoryDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java,
                        "history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance

            }
        }
    }
}