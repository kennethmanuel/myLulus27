package com.nmp.mylulus27

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_kelulusan.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragmentKelulusan.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragmentKelulusan : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var ipk:Double = 0.0
    var ipk_min:Double = 0.0
    var sks:Int = 0
    var sks_min:Int = 0
    var nilai_d:Int = 0
    var nilai_d_max:Int = 0
    var kesimpulan = ""
    var message = ""
    var messageTidakLulus = "Saya belum bisa lulus menurut app myLulus."
    var messageLulus = "Saya dinyatakan lulus dari app myLulus!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun volley(){
        val q = Volley.newRequestQueue(activity)
        val url = "http://10.0.2.2/mylulus/cek_lulus.php?nrp=${Global.nrp}"

        val stringRequest = object : StringRequest(
                Method.GET, url,
                {
                    //to get the JSON obj
                    val obj = JSONObject(it)
                    //first, check the result key
                    if(!obj.has("error")) {
                        if (obj.getString("status").equals("ok")) {
                            //get the JSON obj
                            ipk = String.format("%.2f", obj.getDouble("ipk")).toDouble()
                            ipk_min = String.format("%.2f", obj.getDouble("ipk_min")).toDouble()
                            sks = obj.getInt("sks")
                            sks_min = obj.getInt("sks_min")
                            nilai_d = obj.getInt("nilai_d")
                            nilai_d_max = obj.getInt("nilai_d_max")
                            kesimpulan = obj.getString("kesimpulan")

                            txtTotalSKSDiambilCekKelulusan.text = "Total SKS: $sks/$sks_min"
                            txtIPKCekKelulusan.text = "IPK: $ipk/$ipk_min"
                            txtNilaiDcekKelulusan.text = "Total SKS dengan nisbi D: $nilai_d/$nilai_d_max"

                            //Pengecekan
                            if (ipk < ipk_min) {
                                txtIPKCekKelulusan.setTextColor(Color.parseColor("#710C04"))

                            } else {
                                txtIPKCekKelulusan.setTextColor(Color.parseColor("#006064"))
                            }

                            if (sks < sks_min) {
                                txtTotalSKSDiambilCekKelulusan.setTextColor(Color.parseColor("#710C04"))

                            } else {
                                txtTotalSKSDiambilCekKelulusan.setTextColor(Color.parseColor("#006064"))
                            }

                            if (nilai_d > nilai_d_max) {
                                txtNilaiDcekKelulusan.setTextColor(Color.parseColor("#710C04"))

                            } else {
                                txtNilaiDcekKelulusan.setTextColor(Color.parseColor("#006064"))
                            }

                            if (kesimpulan.equals("ng")) {
                                txtLulusCekLulus.text = "TIDAK LULUS"
                                message = messageTidakLulus
                                txtLulusCekLulus.setTextColor(Color.parseColor("#710C04"))
                            } else {
                                message = messageLulus
                                txtLulusCekLulus.text = "LULUS"
                            }
                        }
                    } else {
                        txtTotalSKSDiambilCekKelulusan.text = "Total SKS: $nilai_d/$nilai_d_max"
                        txtTotalSKSDiambilCekKelulusan.setTextColor(Color.parseColor("#710C04"))

                        txtIPKCekKelulusan.text = "IPK: $ipk/$ipk_min"
                        txtIPKCekKelulusan.setTextColor(Color.parseColor("#710C04"))

                        txtNilaiDcekKelulusan.text = "Total SKS dengan nisbi D: $nilai_d/$nilai_d_max"
                        txtNilaiDcekKelulusan.setTextColor(Color.parseColor("#710C04"))

                        txtLulusCekLulus.text = "TIDAK LULUS"
                        txtLulusCekLulus.setTextColor(Color.parseColor("#710C04"))

                        Toast.makeText(context, obj.getString("error"), Toast.LENGTH_SHORT).show()
                    }
                },
                {
                    Log.e("apiresult", it.message.toString())
                }
        ){
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        q.add(stringRequest)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        volley()
        fabWhatsApp.setOnClickListener {
            //WHATSAPP INTENT
            val sendIntent = Intent(Intent.ACTION_VIEW).apply {
                this.data = Uri.parse("https://api.whatsapp.com/send?phone=+628985728990&text=" + message)
            }

            //Android Sharesheet gives users the ability to share info with the right person, relevant app suggestions, all with a single tap.
            val shareIntent = Intent.createChooser(sendIntent, "Kirim pesan menggunakan")
            startActivity(shareIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        volley()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kelulusan, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragmentKelulusan.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragmentKelulusan().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}