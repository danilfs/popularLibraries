package com.example.gitapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), INavController {
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