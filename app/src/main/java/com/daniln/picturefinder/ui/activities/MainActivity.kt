package com.daniln.picturefinder.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.daniln.AndroidApplication
import com.daniln.picturefinder.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_search -> {
                    Navigation.findNavController(this,
                        R.id.nav_host_fragment
                    )
                        .navigate(R.id.imagesFragment)
                    true
                }
                R.id.action_history -> {
                    Navigation.findNavController(this,
                        R.id.nav_host_fragment
                    )
                        .navigate(R.id.usersFragment)

                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this,
            R.id.nav_host_fragment
        ).navigateUp()
}
