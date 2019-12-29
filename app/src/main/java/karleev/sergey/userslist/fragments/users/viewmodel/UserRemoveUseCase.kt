package karleev.sergey.userslist.fragments.users.viewmodel

import karleev.sergey.userslist.core.local.user.User
import kotlinx.coroutines.runBlocking

class UserRemoveUseCase(val repo: UsersRepo) {
    fun remove(user: User) = runBlocking { repo.deleteUser(user) }
    fun restore(user: User) = runBlocking { repo.addUser(user) }

}
