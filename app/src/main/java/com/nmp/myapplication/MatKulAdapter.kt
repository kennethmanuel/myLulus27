package com.nmp.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_card_matkul.view.*

class MatKulAdapter(val matkuls:ArrayList<MataKuliah>)
    : RecyclerView.Adapter<MatKulAdapter.matkulViewHolder>() {

    class matkulViewHolder(val v: View): RecyclerView.ViewHolder(v){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): matkulViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.activity_card_matkul, parent,false)
        return matkulViewHolder(v)

    }

    override fun onBindViewHolder(holder: matkulViewHolder, position: Int) {
        var matakul = matkuls[position]
        holder.v.textViewKodeMatkul.text = matakul.kode
        holder.v.textViewNamaMatkul.text = matakul.nama
        holder.v.textViewSKSMatkul.text = matakul.sks.toString() + " Sks"
    }

    override fun getItemCount()= matkuls.size
}