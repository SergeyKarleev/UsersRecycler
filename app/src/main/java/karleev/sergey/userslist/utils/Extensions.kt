package karleev.sergey.userslist.utils

import android.view.View

/**
 * Created by Sergey Karleev on 29.12.2019.
 */
fun View.showOrGone(condition: Boolean?) {
    visibility = if (condition == true) View.VISIBLE else View.GONE
}

fun View.showOrHide(condition: Boolean?) {
    visibility = if (condition == true) View.VISIBLE else View.INVISIBLE
}