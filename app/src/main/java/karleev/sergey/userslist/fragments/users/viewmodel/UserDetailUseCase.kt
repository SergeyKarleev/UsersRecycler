package karleev.sergey.userslist.fragments.users.viewmodel

import karleev.sergey.userslist.core.local.user.User
import kotlinx.coroutines.runBlocking

class UserDetailUseCase(private val userRepo: UsersRepo) {
    fun findUser(uid: Long): User? = runBlocking {
        userRepo.findLocalUser(uid)
    }

}
