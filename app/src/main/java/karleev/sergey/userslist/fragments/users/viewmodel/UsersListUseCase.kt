package karleev.sergey.userslist.fragments.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import karleev.sergey.userslist.core.local.Database
import karleev.sergey.userslist.core.local.user.User
import karleev.sergey.userslist.core.remote.LiveResponse
import karleev.sergey.userslist.core.remote.models.response.GetUsersResponseModel

class UsersListUseCase(private val userRepo: UsersRepo) {

    val usersResponseLiveData = MutableLiveData<LiveResponse<GetUsersResponseModel>>()
    val usersLiveData: LiveData<PagedList<User>>

    val hasNext
        get() = (usersResponseLiveData.value?.model?.hasMore ?: 1 == 1)

    private val pageTokens = mutableListOf("vboaf", "1bkt3r")

    init {
        val factory = Database.db.userDao().getUsersPaged()
        val livePagedBuilder = LivePagedListBuilder(factory, 10)
            .setBoundaryCallback(UsersBoundaryCallback())
        usersLiveData = livePagedBuilder.build()
    }

    fun fetchUsers() {
        if (pageTokens.isNullOrEmpty()) return

        val pageToken = pageTokens.get(0)

        userRepo.fetchUsers(pageToken) {
            usersResponseLiveData.value = it
        }
    }

    inner class UsersBoundaryCallback : PagedList.BoundaryCallback<User>() {
        override fun onItemAtEndLoaded(itemAtEnd: User) {
            super.onItemAtEndLoaded(itemAtEnd)
            if (hasNext && usersLiveData.value != null) {
                fetchUsers()
                if (pageTokens.size > 0) pageTokens.removeAt(0)
            }
        }
    }
}
