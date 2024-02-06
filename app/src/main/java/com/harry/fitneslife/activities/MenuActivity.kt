package com.harry.fitneslife.activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.harry.fitneslife.R
import com.harry.fitneslife.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var  bindig : ActivityMenuBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(bindig.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar_menu)
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(this,bindig.drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        bindig.drawerLayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        bindig.navView.setNavigationItemSelectedListener(this)



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navInicio ->{
            val intem  = Intent(this,HomeActivity::class.java)
            startActivity(intem)
            }
            R.id.navEjercicios->{
                val intem  = Intent(this,HomeActivity::class.java)
                startActivity(intem)
            }
            R.id.navRutinas->{
                val intem  = Intent(this,HomeActivity::class.java)
                startActivity(intem)
            }
            R.id.navCuerpo->{val intem  = Intent(this,HomeActivity::class.java)
                startActivity(intem)
            }
            R.id.navConfiguracion->{val intem  = Intent(this,HomeActivity::class.java)
                startActivity(intem)
            }
            R.id.navCuenta->{val intem  = Intent(this,HomeActivity::class.java)
                startActivity(intem)
            }
        }
        bindig.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}