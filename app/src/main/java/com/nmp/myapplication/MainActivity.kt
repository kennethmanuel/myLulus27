package com.nmp.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMasuk.setOnClickListener {
            val nrp = inputNRP.text.toString()
            val pin = inputPin.text.toString()

            if (nrp.isNotEmpty() && pin.isNotEmpty()) {
                if ()
                val q = Volley.newRequestQueue(this)
                val url = "http://10.0.2.2/mylulus/cek_login.php"
                val stringRequest = object : StringRequest(
                    Method.POST,
                    url,
                    {
                        Log.d("Login", it.toString())
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
            } else {
                Toast.makeText(this, "NRP atau/dan Pin harus diisi terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}