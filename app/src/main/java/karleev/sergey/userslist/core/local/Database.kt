package karleev.sergey.userslist.core.local

import android.content.Context
import androidx.room.Room

/**
 * Created by Sergey Karleev on 29.12.2019.
 */
object Database {

    lateinit var db: AppDatabase
        private set

    fun init(applicationContext: Context) {
        if (::db.isInitialized == true) return
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database")
            .build()
    }
}