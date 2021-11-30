package com.lnm011223.foods_secret

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.lnm011223.foods_secret.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_search, R.id.nav_select, R.id.nav_tag, R.id.nav_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_home) {
                binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#00c48c"))
                window.statusBarColor = Color.parseColor("#00c48c")
            }
            if (destination.id == R.id.nav_search) {
                binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#0084f4"))
                window.statusBarColor = Color.parseColor("#0084f4")
            }
            if (destination.id == R.id.nav_select) {
                binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#EB8C8F"))
                window.statusBarColor = Color.parseColor("#EB8C8F")
            }
            if (destination.id == R.id.nav_tag) {
                binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#2573f9"))
                window.statusBarColor = Color.parseColor("#2573f9")
            }
            if (destination.id == R.id.nav_about) {
                binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#ffa26b"))
                window.statusBarColor = Color.parseColor("#ffa26b")
            }



        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}


