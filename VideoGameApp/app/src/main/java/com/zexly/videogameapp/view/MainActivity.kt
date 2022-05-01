package com.zexly.videogameapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zexly.videogameapp.R
import com.zexly.videogameapp.model.Result
import com.zexly.videogameapp.viewmodel.GameListViewModel
import kotlinx.android.synthetic.main.fragment_game_list.*

class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: GameListFragment
    lateinit var favoriteFragment: FavoriteFragment
    private lateinit var adapter: ArrayAdapter<*>
    lateinit var liste:MutableLiveData<List<Result>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       /* homeFragment= GameListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layoutAna,homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()*/


    /*    serchbarId.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return true
            }

        })*/

       val bottomNavigation:BottomNavigationView=findViewById(R.id.bottomNavView)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){

                R.id.home ->{
                    homeFragment= GameListFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.favorite ->{
                    favoriteFragment= FavoriteFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,favoriteFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()

                }
            }
            true
        }
    }
}