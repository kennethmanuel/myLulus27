package com.nmp.mylulus27

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import kotlinx.android.synthetic.main.fragment_matkul.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [fragmentMatkul.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragmentMatkul : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var v:View ?= null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_matkul, container, false)
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(Global.matkuls.size == 0){
            matKul()
        }
        mhsAmbilMK()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            arguments?.let {
            }
        }
    }

    //Tahap 1: Ambil semua data mata kuliah & masukkan ke dalam variabel Global
    fun matKul(){
        val q = Volley.newRequestQueue(activity)
        val url = "http://10.0.2.2/mylulus/get_matkul.php"

        var stringRequest = StringRequest(
                Request.Method.POST, url,
                {
                    Log.d("apiresult", it)

                    val obj = JSONObject(it)

                    if (obj.getString("result") == "OK"){
                        val data = obj.getJSONArray("message")

                        for (i in 0 until data.length()){
                            var matkulObj = data.getJSONObject(i)

                            var matkulss = MataKuliah(
                                    matkulObj.getString("kode"),
                                    matkulObj.getString("nama"),
                                    matkulObj.getInt("sks")
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

    //Tahap 2: Ambil semua data mata kuliah yang diambil mahasiswa
    fun mhsAmbilMK(){
        var mhsAmbilMks:ArrayList<MhsAmbilMk> = ArrayList()
        var kuliahs:ArrayList<String> = ArrayList()
        var matkulsNow:ArrayList<MatkulNow> = ArrayList()
        val q = Volley.newRequestQueue(activity)
        val url = "http://10.0.2.2/mylulus/get_mahasiswa_ambil_mk.php"

        val stringRequest = object : StringRequest(
                Method.POST,
                url,
                {
                    val obj = JSONObject(it)

                    if(obj.getString("result") == "OK"){
                        val data = obj.getJSONArray("message")

                        for (i in 0 until data.length()){
                            var ambilObj = data.getJSONObject(i)

                            var mhsAmbilMkss = MhsAmbilMk(
                                    ambilObj.getString("nrp"),
                                    ambilObj.getString("kode_mk"),
                                    ambilObj.getString("semester"),
                                    ambilObj.getInt("tahun_ambil"),
                                    ambilObj.getString("nisbi"),
                                    ambilObj.getString("nama"),
                                    ambilObj.getString("sks")
                            )
                            mhsAmbilMks.add(mhsAmbilMkss)
                            kuliahs.add(mhsAmbilMkss.kode)
                        }
                    }
                    for(i in 0 until Global.matkuls.size){
                        var j = kuliahs.indexOf(Global.matkuls.get(i).kode)
                        Log.d("cekisiarray",j.toString())
                        if(j == -1){
                            matkulsNow.add(MatkulNow(1, Global.matkuls.get(i).kode, Global.matkuls.get(i).nama, Global.matkuls.get(i).sks.toString(), "",0,""))
                        } else {
                            matkulsNow.add(MatkulNow(2, Global.matkuls.get(i).kode, Global.matkuls.get(i).nama, Global.matkuls.get(i).sks.toString(), mhsAmbilMks.get(j).semester,mhsAmbilMks.get(j).tahun,mhsAmbilMks.get(j).nisbi))
                        }
                    }
                    Log.d("cekisiarray",mhsAmbilMks.toString())
                    Log.d("cekisiarray",kuliahs.toString())
                    updateList(matkulsNow)
                },
                {
                    Toast.makeText(context, "Data gagal diubah. Silahkan cek ulang kembali isian data.", Toast.LENGTH_LONG).show()
                }
        ) {
            //menginjeksikan data yang akan dikirimkan ke API
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["submit"] = "Data exists."
                params["nrp"] = Global.nrp
                return params
            }
        }
        q.add(stringRequest)
    }

    fun updateList(mk:ArrayList<MatkulNow>) {
        val lm = LinearLayoutManager(activity)
        view?.matkulView?.let{
            it.layoutManager = lm
            it.setHasFixedSize(true)
            //init the adapter
            //context obj can be accessed through parent activity
            it.adapter = MatKulAdapter(activity!!.applicationContext, mk)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        mhsAmbilMK()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragmentMatkul.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragmentMatkul().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}