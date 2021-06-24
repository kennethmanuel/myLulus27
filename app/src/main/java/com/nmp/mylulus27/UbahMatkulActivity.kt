package com.nmp.mylulus27

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_add_mata_kuliah.*
import kotlinx.android.synthetic.main.activity_add_mata_kuliah.rdoGasal
import kotlinx.android.synthetic.main.activity_add_mata_kuliah.rdoGenap
import kotlinx.android.synthetic.main.activity_ubah_matkul.*
import org.json.JSONObject

class UbahMatkulActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_matkul)

        //Ambil konten matkul & set text-nya
        val matkulKode = intent.getStringExtra(Global.KODE_MATKUL)
        val namaMatkul = intent.getStringExtra(Global.NAMA_MATKUL)
        val sksMatkul = intent.getStringExtra(Global.SKS_MATKUL)
        val smt = intent.getStringExtra(Global.SEMESTER)
        val thn = intent.getStringExtra(Global.TAHUN)
        val getNisbi = intent.getStringExtra(Global.NISBI)

        txtKodeMatkul5.text = matkulKode
        txtMatkul5.text = namaMatkul
        txtSks5.text = "$sksMatkul SKS"

        if(smt.equals("genap")){
            rdoGenap.isChecked = true
        } else {
            rdoGasal.isChecked = true
        }

        inpThn.setText(thn)

        //Set adapter untuk nisbi
        val adapter = ArrayAdapter(this, R.layout.myspinner_layout, Global.nisbi)
        adapter.setDropDownViewResource(R.layout.myspinner_item_layout)
        spinNisbi5.adapter = adapter

        when(getNisbi){
            "A" -> {
                spinNisbi5.setSelection(0)
            }
            "AB" -> {
                spinNisbi5.setSelection(1)
            }
            "B" -> {
                spinNisbi5.setSelection(2)
            }
            "BC" -> {
                spinNisbi5.setSelection(3)
            }
            "C" -> {
                spinNisbi5.setSelection(4)
            }
            "D" -> {
                spinNisbi5.setSelection(5)
            }
            "E" -> {
                spinNisbi5.setSelection(6)
            }
        }

        btnKembali5.setOnClickListener {
            finish()
        }

        btnSimpan5.setOnClickListener {
            var semester = ""
            var nisbi = spinNisbi5.getSelectedItem().toString()
            var thnAjaran = inpThn.text.toString()
            if (thnAjaran.isNotEmpty() && nisbi.isNotEmpty() && (rdoGasal.isChecked || rdoGenap.isChecked)) {
                if(rdoGenap.isChecked()){
                    semester = "Genap"
                }
                else{
                    semester = "Gasal"
                }
                val q = Volley.newRequestQueue(this)
                val url = "http://10.0.2.2/mylulus/ubah_mahasiswa_mk.php"
                val stringRequest = object : StringRequest(
                        Method.POST,
                        url,
                        {
                            val data = JSONObject(it)
                            if(data.getString("result") == "OK"){
                                Log.d("halo",data.getString("message"))
                                Toast.makeText(this, data.getString("message"), Toast.LENGTH_SHORT).show()
                                finish()
                            } else{
                                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
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
                        params["kode"] = matkulKode.toString()
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