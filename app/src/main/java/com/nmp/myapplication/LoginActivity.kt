package com.nmp.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
                    val url = "http://10.0.2.2/mylulus/cek_login.php"
                    val stringRequest = object : StringRequest(
                        Method.POST,
                        url,
                        {
                            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                            success = 1
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

                    if(success == 1) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
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