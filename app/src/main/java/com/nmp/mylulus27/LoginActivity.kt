package com.nmp.mylulus27

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var success = 0
        btnMasuk.setOnClickListener {
            val nrp = inputNRP.text.toString()
            val pin = inputPin.text.toString()

            if (nrp.isNotEmpty() && pin.isNotEmpty()) {
                if (nrp.length == 9 && pin.length == 8){
                    val q = Volley.newRequestQueue(this)
                    val url = "http://10.0.2.2/mylulus/get_mahasiswa.php"
                    val stringRequest = object : StringRequest(
                        Method.POST, url,
                        {
                            //to get the JSON obj
                            val obj = JSONObject(it)

                            //first, check the result key
                            if(obj.getString("result") == "OK"){
                                //get the JSON obj
                                val data = obj.getJSONArray("message")
                                val mhsObj = data.getJSONObject(0)

                                //put mahasiswa NRP, nama, and angkatan to global
                                Global.nrp = mhsObj.getString("nrp")
                                Global.nama = mhsObj.getString("nama")
                                Global.angkatan = mhsObj.getInt("angkatan")
                                Toast.makeText(this, "Selamat datang, ${Global.nama}.", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                val data = obj.getString("message")
                                Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                            }
                        },
                        {
                            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        override fun getParams(): MutableMap<String, String> {
                            val params = HashMap<String, String>()
                            params["submit"] = "Data exists."
                            params["nrp"] = nrp
                            params["pin"] = pin
                            return params
                        }
                    }
                    q.add(stringRequest)
                }
                else{
                    Toast.makeText(this, "NRP atau/dan Pin memiliki panjang tidak sesuai kriteria!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "NRP atau/dan Pin harus diisi terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
        btnDaftar.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}