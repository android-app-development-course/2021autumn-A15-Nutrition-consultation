package com.lnm011223.foods_secret

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.WindowManager
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
import androidx.annotation.NonNull

import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.navigation.NavController


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

        appbarcolor(navController)
        val insetsController = WindowCompat.getInsetsController(
            window, window.decorView
        )
        window.navigationBarColor = Color.WHITE
        insetsController?.apply { isAppearanceLightNavigationBars = true }





        drawerLayout.addDrawerListener(object : DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                //抽屉正在滑动时调用
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    //部分可见就会进入
                    window.statusBarColor = Color.TRANSPARENT
                }
                else{
                    appbarcolor(navController)
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                //抽屉完全打开后调用


            }

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerStateChanged(newState: Int) {}
        })

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
    fun appbarcolor(navController:NavController){//用于改变actionbar和statusbar颜色
        navController.addOnDestinationChangedListener { _, destination, _ -> when(destination.id){
            R.id.nav_home -> {binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#00c48c"))
                window.statusBarColor = Color.parseColor("#00c48c")}
            R.id.nav_search -> {binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#0084f4"))
                    window.statusBarColor = Color.parseColor("#0084f4")}
            R.id.nav_select -> {binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#EB8C8F"))
                    window.statusBarColor = Color.parseColor("#EB8C8F")}
            R.id.nav_tag -> {binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#ECCE5F"))
                    window.statusBarColor = Color.parseColor("#ECCE5F")}
            R.id.nav_about -> {binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#ffa26b"))
                    window.statusBarColor = Color.parseColor("#ffa26b")}
        }

        }
    }

}


