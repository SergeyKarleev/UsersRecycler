package karleev.sergey.userslist.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import karleev.sergey.userslist.core.local.user.Converters
import karleev.sergey.userslist.core.local.user.User
import karleev.sergey.userslist.core.local.user.UserDao


/**
 * Created by Sergey Karleev on 29.12.2019.
 */
@Database(entities = [User::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
