package com.julia.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.julia.myapplication.databinding.ActivityMainBinding
import com.julia.myapplication.databinding.FragmentTrackListBinding
import com.julia.myapplication.ui.FavoriteFragment
import com.julia.myapplication.ui.TrackListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TrackListFragment())
                .commit()
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_tracks -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, TrackListFragment())
                        .commit()
                    true
                }
                R.id.nav_favorites -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FavoriteFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}