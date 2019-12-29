package karleev.sergey.userslist.core.remote

import karleev.sergey.userslist.core.remote.models.response.GetUsersResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Sergey Karleev on 27.05.2019.
 */
class RemoteApi {

    interface UserApi {
        @GET("bins/{page}/")
        fun getUsers(@Path("page") page: String): Deferred<Response<GetUsersResponseModel>>
    }
}
