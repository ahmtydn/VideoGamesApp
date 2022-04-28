package com.zexly.videogameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var homeFragment:GameListFragment
    lateinit var favoriteFragment:FavoriteFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeFragment= GameListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layoutAna,homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        val bottomNavigation:BottomNavigationView=findViewById(R.id.bottomNavView)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){

                R.id.home->{
                    homeFragment= GameListFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layoutAna,homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.favorite->{
                    favoriteFragment= FavoriteFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layoutAna,favoriteFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }
    }
}