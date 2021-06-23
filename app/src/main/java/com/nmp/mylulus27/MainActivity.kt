package com.nmp.mylulus27

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
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
                val sendIntent = Intent().apply {
                    //Intent action -> sebuah konstanta String yang menspesifikasikan aksi generic apa yang akan kita pilih.
                    this.action = Intent.ACTION_SEND
                    //URI data -> If the picked action is ACTION_SEND -> need to specify what type of data that'll be sent.
                    this.data = Uri.parse("mailto:")
                    this.type = "text/plain"
                    //Optionally, you can add extras to include more info, such as email recipients (EXTRA_EMAIL, EXTRA_CC, dll), the email subjects (EXTRA_SUBJECT), dll.
                    this.putExtra(Intent.EXTRA_EMAIL, arrayOf("mylulus@unit.ubaya.ac.id"))
                    this.putExtra(Intent.EXTRA_SUBJECT, "Bug Aplikasi")
                    this.putExtra(Intent.EXTRA_TEXT, "Halo, perkenalkan nama saya ${Global.nama} dengan NRP ${Global.nrp}. Saya ingin menginformasikan bahwa terdapat bug dalam aplikasi yang mengganggu aktivitas saya. Terima kasih.")
                }

                //Android Sharesheet gives users the ability to share info with the right person, relevant app suggestions, all with a single tap.
                val shareIntent = Intent.createChooser(sendIntent, "Kirim pesan menggunakan")
                startActivity(shareIntent)
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