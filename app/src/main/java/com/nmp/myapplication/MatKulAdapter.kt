package com.nmp.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_card_matkul.*
import kotlinx.android.synthetic.main.activity_card_matkul.view.*

class MatKulAdapter(val ctx:Context): RecyclerView.Adapter<MatKulAdapter.matkulViewHolder>() {

    class matkulViewHolder(val v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): matkulViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.activity_card_matkul, parent,false)
        return matkulViewHolder(v)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: matkulViewHolder, position: Int) {
        var matakul = Global.matkuls[position]
        with(holder.v) {
            textViewKodeMatkul.text = matakul.kode
            textViewNamaMatkul.text = matakul.nama
            textViewSKSMatkul.text = matakul.sks.toString() + " SKS"
            btnAmbil.setOnClickListener {
                val intent = Intent(ctx, AddMatKulActivity::class.java)
                intent.putExtra(Global.KODE_MATKUL, textViewKodeMatkul.text.toString())
                intent.putExtra(Global.NAMA_MATKUL, textViewNamaMatkul.text.toString())
                intent.putExtra(Global.SKS_MATKUL, textViewSKSMatkul.text.toString())
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                ctx.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = Global.matkuls.size
}