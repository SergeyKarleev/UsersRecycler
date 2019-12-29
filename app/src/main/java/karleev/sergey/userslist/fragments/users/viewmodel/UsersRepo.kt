package karleev.sergey.userslist.fragments.users.viewmodel

import karleev.sergey.userslist.core.local.Database
import karleev.sergey.userslist.core.local.user.User
import karleev.sergey.userslist.core.remote.ApiFactory
import karleev.sergey.userslist.core.remote.BaseRepo
import karleev.sergey.userslist.core.remote.LiveResponse
import karleev.sergey.userslist.core.remote.models.response.GetUsersResponseModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created by Sergey Karleev on 29.12.2019.
 */
class UsersRepo : BaseRepo() {
    val userDao = Database.db.userDao()

    fun fetchUsers(page: String, callback: ((LiveResponse<GetUsersResponseModel>) -> Unit)) {
        val mediatorCallback: (LiveResponse<GetUsersResponseModel>) -> Unit = {
            launch {
                it.model?.user?.let {
                    userDao.insert(it)
                    userDao.getUsersPaged().create().invalidate()
                }
                main { callback.invoke(it) }
            }
        }

        sendRequest(ApiFactory.userApi.getUsers(page), mediatorCallback)
    }

    suspend fun findLocalUser(uid: Long): User? =
        async { userDao.getUser(uid) }.await()

    suspend fun deleteUser(user: User) =
        async {
            userDao.delete(user)
            userDao.getUsersPaged().create().invalidate()
        }.await()

    suspend fun addUser(user: User) = async {
        userDao.insert(user)
        userDao.getUsersPaged().create().invalidate()
    }.await()
}