package com.nmp.mylulus27

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class UbahMatkulActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_matkul)

        //Ambil konten matkul & set text-nya
        val kodeMatkul = intent.getStringExtra(Global.KODE_MATKUL)
        val namaMatkul = intent.getStringExtra(Global.NAMA_MATKUL)
        val sksMatkul = intent.getStringExtra(Global.SKS_MATKUL)

        fun volley(){
            val q = Volley.newRequestQueue(this)
            val url = "http://10.0.2.2/mylulus/ubah_matkul.php"

            var stringRequest = StringRequest(
                Request.Method.POST, url,
                {
                    Log.d("apiresult", it)

                    val obj = JSONObject(it)

                    if (obj.getString("result") == "OK"){
                        val data = obj.getJSONArray("data")

                        for (i in 0 until data.length()){
                            var playObj = data.getJSONObject(i)

                            var matkulss = MataKuliah(
                                playObj.getString("kode"),
                                playObj.getString("nama"),
                                playObj.getInt("sks")
                            )
                            Global.matkuls.add(matkulss)
                        }
                        Log.d("cekisiarray",Global.matkuls.toString())
                    }
                },
                {
                    Log.e("apiresult", it.message.toString())
                })
            q.add(stringRequest)
        }
    }
}