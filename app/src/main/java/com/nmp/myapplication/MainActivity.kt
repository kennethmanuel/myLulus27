package com.nmp.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.view.*
import kotlinx.android.synthetic.main.drawer_layout.*

class MainActivity : AppCompatActivity() {
    val fragments:ArrayList<Fragment> = ArrayList()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        
        //TOOLBAR SETUP
        setContentView(R.layout.drawer_layout)
        toolbar?.title = "myLulus"
        toolbar?.setTitleTextColor(Color.parseColor("#FFFFFF"))
        //Display hamburger icon
        //No longer set home icon as back button
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        //Tie together the funct. of DrawerLayout & framework ActionBar -> implement the recommended design for nav. drawers.
        var drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        //To show hamburger icon
        drawerToggle.isDrawerIndicatorEnabled = true
        //To respond open/close drawer
        drawerToggle.syncState()


        with(fragments){
            add(fragmentMatkul())
            add(fragmentKelulusan())
            add(fragmentProfil())
        }

        viewPager.adapter = MyAdapter(this, fragments)
        //buat listener OnPageChanged & anonymous objek di const pageChange
        viewPager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            //position di sini menunjukkan posisi halaman fragment yang aktif. Hlm ViewPager start dari 0.
            //jadi akan dipanggil jika position viewPager berubah
            override fun onPageSelected(position: Int) {
                bottomNav.selectedItemId = bottomNav.menu.getItem(position).itemId
            }
        })

        bottomNav.setOnNavigationItemSelectedListener {
            //returnnya boolean. true -> menu dpt diklik. false -> menu akan mjd disable
            viewPager.currentItem = when(it.itemId){
                //update view pager active page
                R.id.itemKuliah -> 0
                R.id.itemKelulusan -> 1
                R.id.itemProfil -> 2
                else -> 0
            }
            //untuk lambda fun, TDK PERLU tulis RETURN
            true
        }

        navView.setNavigationItemSelectedListener {
            var curItem = 0
            if(it.itemId == R.id.itemKuliah){
                it.setChecked(true)
                curItem = 0
            }
            else if (it.itemId == R.id.itemKelulusan){
                it.setChecked(true)
                curItem = 1
            }
            else if (it.itemId == R.id.itemProfil){
                it.setChecked(true)
                curItem = 2
            }
            else if (it.itemId == R.id.itemHubungi){
                it.setChecked(true)
            }
            else if (it.itemId == R.id.itemKeluar){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            viewPager.currentItem = curItem
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
}