package com.nmp.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_layout.*

class MainActivity : AppCompatActivity() {
    val fragments:ArrayList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_layout)

        //Display hamburger icon
        //No longer set home icon as back button
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        //Tie together the funct. of DrawerLayout & framework ActionBar -> implement the recommended design for nav. drawers.
        var drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        //To show hamburger icon
        drawerToggle.isDrawerIndicatorEnabled = true
        //To respond open/close drawer
        drawerToggle.syncState()

        fragments.add(fragmentMatkul())
        fragments.add(fragmentKelulusan())
        fragments.add(fragmentProfil())
        viewPager.adapter = MyAdapter(this, fragments)
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {

            }
        })

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.itemKuliah -> Toast.makeText(this, "Kuliah", Toast.LENGTH_SHORT).show()
                R.id.itemKelulusan -> Toast.makeText(this, "Kelulusan", Toast.LENGTH_SHORT).show()
                R.id.itemProfil -> Toast.makeText(this, "Profil", Toast.LENGTH_SHORT).show()
                R.id.itemHubungi -> Toast.makeText(this, "Hubungi", Toast.LENGTH_SHORT).show()
                R.id.itemKeluar -> Toast.makeText(this, "hubungi", Toast.LENGTH_SHORT).show()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
}