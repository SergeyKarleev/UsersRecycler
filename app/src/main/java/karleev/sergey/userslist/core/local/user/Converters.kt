package karleev.sergey.userslist.core.local.user

import androidx.room.TypeConverter
import com.google.gson.Gson
import karleev.sergey.userslist.core.remote.models.response.Message


/**
 * Created by Sergey Karleev on 29.12.2019.
 */
class Converters {
    @TypeConverter
    fun fromMessage(message: Message?): String {
        return Gson().toJson(message)
    }

    @TypeConverter
    fun toMessage(data: String): Message? {
        return Gson().fromJson(data, Message::class.java)
    }
}