package com.nmp.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_card_matkul.*
import kotlinx.android.synthetic.main.activity_card_matkul.view.*
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
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            arguments?.let {
            }
        }
    }

    fun volley(){
        val q = Volley.newRequestQueue(activity)
        val url = "http://192.168.0.38/ubaya/get_matkul.php"

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
                        updateList()
                        Log.d("cekisiarray",Global.matkuls.toString())
                    }
                },
                {
                    Log.e("apiresult", it.message.toString())
                })
        q.add(stringRequest)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        volley()
    }

    fun updateList() {
        val lm = LinearLayoutManager(activity)
        view?.matkulView?.let{
            it.layoutManager = lm
            it.setHasFixedSize(true)
            //init the adapter
            //context obj can be accessed through parent activity
            it.adapter = MatKulAdapter(activity!!.applicationContext)
        }
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