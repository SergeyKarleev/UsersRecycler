package karleev.sergey.userslist.fragments.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import karleev.sergey.userslist.core.local.user.User

class UsersViewModel : ViewModel() {

    private val repo = UsersRepo()
    private val userDataUseCase = UsersListUseCase(repo)
    private val userDetailUseCase = UserDetailUseCase(repo)
    private val userRemoveUseCase = UserRemoveUseCase(repo)

    val users: LiveData<PagedList<User>>
        get() = userDataUseCase.usersLiveData

    fun fetchUsers() = userDataUseCase.fetchUsers()

    fun findUser(uid: Long): User? = userDetailUseCase.findUser(uid)
    fun removeUser(user: User?) = user?.let { userRemoveUseCase.remove(it) }
    fun addUser(user: User?) = user?.let { userRemoveUseCase.restore(it) }
}
