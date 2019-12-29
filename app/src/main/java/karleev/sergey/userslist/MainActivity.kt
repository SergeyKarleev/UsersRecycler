package karleev.sergey.userslist

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import karleev.sergey.userslist.fragments.users.viewmodel.UsersViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModels()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == android.R.id.home) {
            findNavController(R.id.nav_host_fragment).navigateUp()
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setupViewModels() {
        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
    }
}
