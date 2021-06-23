package com.nmp.mylulus27

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnMasuk.setOnClickListener {
            val nrp = inputNRP.text.toString()
            val pin = inputPin.text.toString()

            if (nrp.isNotEmpty() && pin.isNotEmpty()) {
                if (nrp.length == 9 && pin.length == 6){
                    val q = Volley.newRequestQueue(this)
                    val url = "http://10.0.2.2/mylulus/cek_login.php"
                    val stringRequest = object : StringRequest(
                        Method.POST,
                        url,
                        {
<<<<<<< Updated upstream:app/src/main/java/com/nmp/myapplication/LoginActivity.kt
                            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
=======
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
>>>>>>> Stashed changes:app/src/main/java/com/nmp/mylulus27/LoginActivity.kt
                        },
                        {
                            Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
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
                else if (nrp.length != 8 || pin.length != 8){
                    Toast.makeText(this, "NRP atau/dan Pin memiliki panjang tidak sesuai kriteria!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "NRP atau/dan Pin harus diisi terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}