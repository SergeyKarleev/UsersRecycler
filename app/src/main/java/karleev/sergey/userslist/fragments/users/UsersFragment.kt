package karleev.sergey.userslist.fragments.users

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import karleev.sergey.userslist.R
import karleev.sergey.userslist.core.local.user.User
import karleev.sergey.userslist.fragments.users.viewmodel.UsersViewModel
import karleev.sergey.userslist.utils.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.users_fragment.*


class UsersFragment : Fragment() {

    companion object {
        fun newInstance() =
            UsersFragment()
    }

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var usersListAdapter: UsersListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.users_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModels()
        setupRecycler()
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.run {
            title = "Сообщения"
            setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun onUserClickListener(user: User) {
        (activity as? AppCompatActivity)?.supportActionBar?.run {
            title = user.shortInfo
            setDisplayHomeAsUpEnabled(true)
        }

        val bundle = bundleOf("uid" to user.uid)
        findNavController().navigate(R.id.detailsFragment, bundle)


    }

    private fun setupRecycler() {
        usersListAdapter = UsersListAdapter(this::onUserClickListener)
        usersList.adapter = usersListAdapter
        usersList.addItemDecoration(
            DividerItemDecoration(
                context!!,
                DividerItemDecoration.VERTICAL
            )
        )
        ItemTouchHelper(SwipeToDeleteUserCallback(activity!!)).attachToRecyclerView(usersList)

    }

    private fun setupViewModels() {
        usersViewModel = ViewModelProviders.of(activity!!).get(UsersViewModel::class.java)
        usersViewModel.users.observe(viewLifecycleOwner, onUsersChangedObserver)
        usersViewModel.fetchUsers()
    }

    private val onUsersChangedObserver = Observer<PagedList<User>> {
        (usersList?.adapter as? UsersListAdapter)?.submitList(it)
    }

    inner class SwipeToDeleteUserCallback(context: Context) : SwipeToDeleteCallback(context) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val user = (viewHolder.itemView.tag as? User)
            usersViewModel.removeUser(user)

            Snackbar
                .make(view!!, "Item was removed from the list.", Snackbar.LENGTH_LONG)
                .apply {
                    setAction("UNDO") {
                        usersViewModel.addUser(user)
                        usersList.scrollToPosition(position)
                    }
                    setActionTextColor(Color.YELLOW)
                }
                .show()


        }
    }
}
