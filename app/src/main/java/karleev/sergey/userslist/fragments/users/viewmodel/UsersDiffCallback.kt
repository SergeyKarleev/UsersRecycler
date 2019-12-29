package karleev.sergey.userslist.fragments.users.viewmodel

import androidx.recyclerview.widget.DiffUtil
import karleev.sergey.userslist.core.local.user.User

/**
 * Created by Sergey Karleev on 29.12.2019.
 */
class UsersDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}