package karleev.sergey.userslist.core.local.user

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*


/**
 * Created by Sergey Karleev on 29.12.2019.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUsers(): DataSource.Factory<Int, User>

    @Query("SELECT * FROM user")
    fun getUsersLive(): LiveData<List<User>>

    @Query("SELECT * FROM user")
    fun getUsersPaged(): DataSource.Factory<Int, User>

    @Query("SELECT * FROM user WHERE uid=:uid")
    fun getUser(uid: Long): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<User>)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}