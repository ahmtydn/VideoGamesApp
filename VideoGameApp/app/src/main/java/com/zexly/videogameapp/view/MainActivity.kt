package com.zexly.videogameapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zexly.videogameapp.R
import com.zexly.videogameapp.adapter.ViewPagerAdapter
import com.zexly.videogameapp.databinding.ActivityMainBinding
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
        val binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      /*  val bottomNavigationView=binding.bottomNavView
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.gameListFragment) as NavHostFragment

        val navController=navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)*/
/*

         homeFragment= GameListFragment()
       supportFragmentManager.beginTransaction()
           .replace(R.id.fragmentContainerView,homeFragment)
           .commit()
*/


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
       /* bottomNavigation.setOnNavigationItemReselectedListener { item ->
            val newlySelectedItemTag = game_graph[item.itemId]
            val selectedFragment = fragmentManager.findFragmentByTag(newlySelectedItemTag)
                    as NavHostFragment?
            val navController = selectedFragment?.navController
            // Pop the back stack to the start destination of the current navController graph
            navController?.popBackStack(
                navController.graph.startDestination, false
            )
        }*/


        
        bottomNavigation.setOnNavigationItemReselectedListener {
            when(it.itemId){

                R.id.gameListFragment ->{
                    val fragmentMenager=supportFragmentManager
                    val fragmentTransaction=fragmentMenager.beginTransaction()
                    val listFragment=GameListFragment()
                    fragmentTransaction.replace(R.id.fragmentContainerView,listFragment).commit()


                }
                R.id.favoriteFragment ->{
                    val fragmentMenager=supportFragmentManager
                    val fragmentTransaction=fragmentMenager.beginTransaction()
                    val favoriteFragment=FavoriteFragment()
                    fragmentTransaction.replace(R.id.fragmentContainerView,favoriteFragment).commit()
                }
            }
            true
        }
    }



}