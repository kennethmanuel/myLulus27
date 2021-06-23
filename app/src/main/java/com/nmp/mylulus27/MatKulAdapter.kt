package com.nmp.mylulus27

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_card_matkul.view.*

//class MatKulAdapter(val ctx:Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    companion object {
//        const val VIEW_TYPE_ONE = 1
//        const val VIEW_TYPE_TWO = 2
//    }

//    private val context: Context = ctx
//    var list: ArrayList<Data> = list

//    class matkulViewHolder(val v: View): RecyclerView.ViewHolder(v){
//        var message: TextView = itemView.findViewById(R.id.textView)
//        fun bind(position: Int) {
//            val recyclerViewModel = list[position]
//            message.text = recyclerViewModel.textData
//        }
//    }
//
//    class ubahMatkulViewHolder(val v: View): RecyclerView.ViewHolder(v){
//        var message: TextView = itemView.findViewById(R.id.textView)
//        fun bind(position: Int) {
//            val recyclerViewModel = list[position]
//            message.text = recyclerViewModel.textData
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): matkulViewHolder {
//        if (viewType == VIEW_TYPE_ONE) {
//            return matkulViewHolder(
//                    LayoutInflater.from(context).inflate(R.layout.activity_card_matkul, parent, false)
//            )
//        }
//        return ubahMatkulViewHolder(
//                LayoutInflater.from(context).inflate(R.layout.activity_ubah_card, parent, false)
//        )
//    }

//    @SuppressLint("SetTextI18n")
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//    }
//
//    override fun getItemCount() = Global.matkuls.size
//}