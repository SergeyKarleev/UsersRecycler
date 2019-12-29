package karleev.sergey.userslist.fragments.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import karleev.sergey.userslist.R
import karleev.sergey.userslist.core.local.user.User
import karleev.sergey.userslist.fragments.users.viewmodel.UsersDiffCallback
import karleev.sergey.userslist.utils.showOrGone
import kotlinx.android.synthetic.main.item_user.view.*


/**
 * Created by Sergey Karleev on 29.12.2019.
 */
class UsersListAdapter(val onUserClickItem: ((User) -> Unit) = {}) :
    PagedListAdapter<User, UsersListAdapter.UserViewHolder>(UsersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User?) = user?.let {
            itemView.tag = user
            itemView.setOnClickListener { onUserClickItem.invoke(user) }

            loadAvatar(user)
            itemView.lastMessageDateTV.text = user.message?.timeDate
            itemView.lastMessageTV.text = user.message?.msg ?: ""
            itemView.userDescriptionTV.text = user.shortInfo
            itemView.badgeTV.text = user.sexText
            itemView.deletedFlagTV.showOrGone(user.deleted == 1)
        }

        fun loadAvatar(user: User) = with(itemView) {

            avatarIV.transitionName = user.foto

            val placeholder = user.placeholder

            if (user.foto == null) {
                avatarIV.setImageResource(placeholder)
                return@with
            }

            Picasso.get().load(user.smallFoto)
                .placeholder(placeholder)
                .error(placeholder)
                .into(avatarIV)

            Picasso.get().load(user.bigFoto)
        }
    }

}


