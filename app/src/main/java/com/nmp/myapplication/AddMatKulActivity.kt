package com.nmp.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.children
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_add_mata_kuliah.*

class AddMatKulActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mata_kuliah)

        //Ambil konten matkul & set text-nya
        val kodeMatkul = intent.getStringExtra(Global.KODE_MATKUL)
        val namaMatkul = intent.getStringExtra(Global.NAMA_MATKUL)
        val sksMatkul = intent.getStringExtra(Global.SKS_MATKUL)

        txtKodeMatkul.text = kodeMatkul
        txtMatkul.text = namaMatkul
        txtSks.text = sksMatkul

        //Set adapter untuk nisbi
        val adapter = ArrayAdapter(this, R.layout.myspinner_layout, Global.nisbi)
        adapter.setDropDownViewResource(R.layout.myspinner_item_layout)
        spinNisbi.adapter = adapter

        btnKembali.setOnClickListener {
            finish()
        }

        btnSimpan.setOnClickListener {
            var semester = ""
            var nisbi = spinNisbi.getSelectedItem().toString()
            var thnAjaran = inputTahun.text.toString()
            if (thnAjaran.isNotEmpty() && nisbi.isNotEmpty() && semester.isNotEmpty()) {
                if(rdoGenap.isChecked()){
                    semester = "Genap"
                }
                else{
                    semester = "Gasal"
                }
                val q = Volley.newRequestQueue(this)
                val url = "http://10.0.2.2/mylulus/tambah_mahasiswa_mk.php"
                val stringRequest = object : StringRequest(
                        Method.POST,
                        url,
                        {
                            Toast.makeText(this, "Data berhasil diubah.", Toast.LENGTH_SHORT).show()
                        },
                        {
                            Toast.makeText(this, "Data gagal diubah. Silahkan cek ulang kembali isian data.", Toast.LENGTH_LONG).show()
                        }
                ) {
                    //menginjeksikan data yang akan dikirimkan ke API
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["submit"] = "Data exists."
                        params["nrp"] = Global.nrp
                        params["kode"] = kodeMatkul.toString()
                        params["semester"] = semester
                        params["tahun"] = thnAjaran
                        params["nisbi"] = nisbi
                        return params
                    }
                }
                q.add(stringRequest)
            } else {
                Toast.makeText(this, "Data wajib terisi semua!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}