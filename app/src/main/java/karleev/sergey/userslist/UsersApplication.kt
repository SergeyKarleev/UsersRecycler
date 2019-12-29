package karleev.sergey.userslist

import android.app.Application
import karleev.sergey.userslist.core.local.Database

/**
 * Created by Sergey Karleev on 29.12.2019.
 */
class UsersApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Database.init(this)
    }
}