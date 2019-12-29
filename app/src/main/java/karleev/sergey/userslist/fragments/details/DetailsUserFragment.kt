package karleev.sergey.userslist.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import karleev.sergey.userslist.R
import karleev.sergey.userslist.core.local.user.User
import karleev.sergey.userslist.fragments.users.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.details_fragment.*


class DetailsUserFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsUserFragment()
    }

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        usersViewModel = ViewModelProviders.of(activity!!).get(UsersViewModel::class.java)
        arguments?.getLong("uid")?.let {
            val user = usersViewModel.findUser(it)
            fillUserInfo(user)
        }
    }

    private fun fillUserInfo(user: User?) = user?.let {
        Picasso.get()
            .load(user.bigFoto)
            .placeholder(user.placeholder)
            .error(user.placeholder)
            .into(detailsAvatarIV)
    }
}
