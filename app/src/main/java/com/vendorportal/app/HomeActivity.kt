package com.vendorportal.app

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.vendorportal.app.databinding.ActivityHomeBinding
import com.vendorportal.app.others.Constants
import com.vendorportal.app.others.MyToast
import com.vendorportal.app.sharedpref.SharedPref

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    lateinit var myToast: MyToast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)


        myToast = MyToast(this)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashboardFragment, R.id.reportsFragment, R.id.ordersFragment
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val sharedPref = SharedPref(this)

        if (sharedPref.getUserAuthStatus()){
       //     myToast.showToast("user logged in")
            navController.navigate(R.id.dashboardFragment)
        }else{
         //   myToast.showToast("user not  logged in")
            navController.navigate(R.id.loginFragment)
        }

        navView.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.logout -> {
                    sharedPref.setUserAuthStatus(false)
                    sharedPref.saveLoginCode("")
                    sharedPref.savePassword("")
                    sharedPref.saveToken("")
                    startActivity(Intent(this,HomeActivity::class.java))
                }
            }

            return@setNavigationItemSelectedListener true
        }

    //   myToast.showToast("function calling")

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when(destination.id) {

                R.id.loginFragment -> hideToolbar()

                R.id.report -> {
                    showToolbar()
                    binding.appBarHome.toolbar.title = Constants.reportName
                }

                else -> showToolbar()
            }
        }

    }

    fun hideToolbar(){
        binding.appBarHome.toolbar.visibility = View.GONE
    }

    fun showToolbar(){
        binding.appBarHome.toolbar.visibility = View.VISIBLE
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}