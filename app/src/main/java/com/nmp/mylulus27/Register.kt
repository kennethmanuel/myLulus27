package com.nmp.mylulus27

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnDaftar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val q = Volley.newRequestQueue(this)
            val url = "http://10.0.2.2/mylulus/register.php"

            if(txtNRP.text.toString().length > 0 && txtPIN.text.toString().length > 0 && txtUlangiPIN.text.toString().length > 0 && txtNama.text.toString().length > 0 && txtTahunMasuk.text.toString().length > 0)
            {
                if(txtNRP.text.toString().length == 9)
                {
                    if (txtPIN.text.toString().length == 8)
                    {
                        if(txtTahunMasuk.text.toString().length == 4)
                        {
                            if(txtPIN.text.toString().equals(txtUlangiPIN.text.toString()))
                            {
                                val nrp = txtNRP.text.toString()
                                val pin = txtPIN.text.toString()
                                val nama = txtNama.text.toString()
                                val tahunMasuk = txtTahunMasuk.text.toString()

                                val stringRequest = object : StringRequest(
                                    Method.POST, url,
                                    {
                                        val data = JSONObject(it)
                                        if(data.getString("result").equals("OK")){
                                            Toast.makeText(this, "Data berhasil ditambah! Silahkan melakukan login kembali.", Toast.LENGTH_SHORT).show()
                                            finish()
                                        }
                                    },
                                    {
                                        Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
                                    }) {
                                    override fun getParams(): MutableMap<String, String> {
                                        val params = HashMap<String, String>()
                                        params.put("submit","Data exists.")
                                        params.put("nrp", nrp)
                                        params.put("pin", pin)
                                        params.put("nama", nama)
                                        params.put("angkatan", tahunMasuk)
                                        return params
                                    }
                                }
                                q.add(stringRequest)
                            }
                            else
                            {
                                builder.setTitle("Kesalahan")
                                builder.setMessage("PIN tidak sama dengan ulangi PIN")
                                builder.setNegativeButton("Ok",{ dialog: DialogInterface?, which: Int -> })
                                builder.show()
                            }
                        }
                        else
                        {
                            builder.setTitle("Kesalahan")
                            builder.setMessage("PIN tidak sama dengan ulangi PIN")
                            builder.setNegativeButton("Ok",{ dialog: DialogInterface?, which: Int -> })
                            builder.show()
                        }
                    }
                    else
                    {
                        builder.setTitle("Kesalahan")
                        builder.setMessage("PIN harus 8 karakter!")
                        builder.setNegativeButton("Ok",{ dialog: DialogInterface?, which: Int -> })
                        builder.show()
                    }
                }
                else
                {
                    builder.setTitle("Kesalahan")
                    builder.setMessage("NRP harus 9 karakter!")
                    builder.setNegativeButton("Ok",{ dialog: DialogInterface?, which: Int -> })
                    builder.show()
                }
            }
            else
            {
                builder.setTitle("Kesalahan")
                builder.setMessage("Semua data harus diisi!")
                builder.setNegativeButton("Ok",{ dialog: DialogInterface?, which: Int -> })
                builder.show()
            }
        }
    }
}