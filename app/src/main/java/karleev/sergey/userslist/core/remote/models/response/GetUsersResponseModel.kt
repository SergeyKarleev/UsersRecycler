package karleev.sergey.userslist.core.remote.models.response

import com.google.gson.annotations.SerializedName
import karleev.sergey.userslist.core.local.user.User
import java.text.SimpleDateFormat
import java.util.*

data class GetUsersResponseModel(
    @SerializedName("errno")
    val errno: Int?,
    @SerializedName("has_more")
    val hasMore: Int?,
    @SerializedName("user")
    val user: List<User>?
) : BaseResponseModel()

data class Message(
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("muid")
    val muid: Int?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("time")
    val time: Int?,
    @SerializedName("ts")
    val ts: Int?,
    @SerializedName("type")
    val type: Int?
) {
    val timeDate: String
        get() {
            val sdf = SimpleDateFormat("dd.MM, hh:mm")
            val timeLong = (time ?: 0) * 1L
            return sdf.format(Date(timeLong))
        }
}
