package com.nmp.mylulus27

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_add_mata_kuliah.*
import org.json.JSONObject

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
            if (thnAjaran.isNotEmpty() && nisbi.isNotEmpty() && (rdoGenap.isChecked || rdoGasal.isChecked)) {
                if(rdoGenap.isChecked){
                    semester = "Genap"
                }
                else{
                    semester = "Gasal"
                }
                Log.d("hahah", "$semester, $nisbi, $thnAjaran")
                val q = Volley.newRequestQueue(this)
                val url = "http://10.0.2.2/mylulus/tambah_mahasiswa_mk.php"
                val stringRequest = object : StringRequest(
                        Method.POST,
                        url,
                        {
                            //to get the JSON obj
                            val obj = JSONObject(it)

                            //first, check the result key
                            if(obj.getString("result") == "OK") {
                                val data = obj.getString("message")
                                Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            else{
                                val data = obj.getString("message")
                                Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                            }
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