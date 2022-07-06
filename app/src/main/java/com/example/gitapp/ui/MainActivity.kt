package com.example.gitapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gitapp.R
import com.example.gitapp.ui.userDetails.UserDetailsFragment
import com.example.gitapp.ui.users.UsersFragment

class MainActivity : AppCompatActivity(), UsersFragment.Controller {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToUserDetails(userId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, UserDetailsFragment.getInstance(userId))
            .addToBackStack(null)
            .commit()
    }

}