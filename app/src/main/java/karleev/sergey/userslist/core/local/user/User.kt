package karleev.sergey.userslist.core.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import karleev.sergey.userslist.R
import karleev.sergey.userslist.core.remote.models.response.Message

@Entity
data class User(
    @SerializedName("age")
    val age: Int?,
    @SerializedName("city")
    val city: Int?,
    @SerializedName("city_n")
    val cityN: String?,
    @SerializedName("client")
    val client: Int?,
    @SerializedName("country")
    val country: Int?,
    @SerializedName("country_n")
    val countryN: String?,
    @SerializedName("dbllike")
    val dbllike: Int?,
    @SerializedName("deleted")
    val deleted: Int?,
    @SerializedName("design_avatar")
    val designAvatar: Int?,
    @SerializedName("elite")
    val elite: Int?,
    @SerializedName("fid")
    val fid: Int?,
    @SerializedName("foto")
    val foto: String?,
    @SerializedName("inmess")
    val inmess: Int?,
    @SerializedName("intim")
    val intim: Int?,
    @SerializedName("karma")
    val karma: String?,
    @SerializedName("lang")
    val lang: String?,
    @SerializedName("lastvisit")
    val lastvisit: Int?,
    @SerializedName("live")
    val live: Int?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("map_lat")
    val mapLat: Double?,
    @SerializedName("map_lon")
    val mapLon: Double?,
    @SerializedName("map_x")
    val mapX: Int?,
    @SerializedName("map_y")
    val mapY: Int?,
    @SerializedName("meeting")
    val meeting: Int?,
    @SerializedName("message")
    val message: Message?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("newmess")
    val newmess: Int?,
    @SerializedName("nick")
    val nick: String?,
    @SerializedName("outmess")
    val outmess: Int?,
    @SerializedName("photo")
    val photo: Int?,
    @SerializedName("region")
    val region: Int?,
    @SerializedName("region_n")
    val regionN: String?,
    @SerializedName("sex")
    val sex: Int?,
    @PrimaryKey
    @SerializedName("uid")
    val uid: Long?,
    @SerializedName("zodiac")
    val zodiac: Int?
) {
    val smallFoto
        get() = foto?.replace("@", "m_")

    val bigFoto
        get() = foto?.replace("@", "b_")

    val sexText
        get() = when (sex) {
            1 -> "М"
            2 -> "Ж"
            else -> ""
        }

    val shortInfo
        get() = "%s, %d".format(name ?: "", age ?: 0).toLowerCase().capitalize()


    val placeholder
        get() = if (sex ?: 1 == 1)
            R.drawable.pl_empty_man_avatar
        else
            R.drawable.pl_empty_woman_avatar

}


