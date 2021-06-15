package com.nmp.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_add_mata_kuliah.*

class AddMataKuliahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mata_kuliah)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, Global.nisbi)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinNisbi.adapter = adapter
    }
}