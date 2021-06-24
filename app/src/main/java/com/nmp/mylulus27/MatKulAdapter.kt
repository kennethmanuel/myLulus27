package com.nmp.mylulus27

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_card_matkul.view.*
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_ubah_card.view.*

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class MatKulAdapter(val ctx:Context, list:ArrayList<MatkulNow>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    var list: ArrayList<MatkulNow> = list
    val typeface = ResourcesCompat.getFont(ctx, R.font.karla)
    val typefaceBody = ResourcesCompat.getFont(ctx, R.font.rubik_bold)

    private inner class matkulViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            val matkul = list[position]
            itemView.textViewKodeMatkul.text = matkul.kodeMatkul
            itemView.textViewKodeMatkul.typeface = typefaceBody
            itemView.textViewNamaMatkul.text = matkul.namaMatkul
            itemView.textViewNamaMatkul.typeface = typeface
            itemView.textViewSKSMatkul.text = "${matkul.sks} SKS"
            itemView.textViewSKSMatkul.typeface = typeface
            itemView.btnAmbil.typeface = typeface
            itemView.btnAmbil.setOnClickListener {
                val intent = Intent(ctx, AddMatKulActivity::class.java)
                intent.putExtra(Global.KODE_MATKUL, matkul.kodeMatkul)
                intent.putExtra(Global.NAMA_MATKUL, matkul.namaMatkul)
                intent.putExtra(Global.SKS_MATKUL, matkul.sks)
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK)
                ctx.startActivity(intent)
            }
        }
    }

    private inner class ubahMatkulViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val matkul = list[position]
            itemView.txtMatkulKode.text = matkul.kodeMatkul
            itemView.txtMatkulKode.typeface = typefaceBody
            itemView.txtMatkulNama.text = matkul.namaMatkul
            itemView.txtMatkulSks.text = "${matkul.sks} SKS"
            itemView.txtMatkulSks.typeface = typeface
            itemView.txtMatkulTahun.text = "(${matkul.semester} ${matkul.tahunAmbil}/${matkul.tahunAmbil.toInt() + 1})"
            itemView.txtMatkulTahun.typeface = typeface
            itemView.txtMatkulNisbi.text = "${matkul.nisbi}"
            itemView.txtMatkulTahun.typeface = typeface
            itemView.btnUbah.setOnClickListener {
                val intent = Intent(ctx, UbahMatkulActivity::class.java)
                intent.putExtra(Global.KODE_MATKUL, matkul.kodeMatkul)
                intent.putExtra(Global.NAMA_MATKUL, matkul.namaMatkul)
                intent.putExtra(Global.SKS_MATKUL, matkul.sks)
                intent.putExtra(Global.SEMESTER, matkul.semester)
                intent.putExtra(Global.TAHUN, matkul.tahunAmbil.toString())
                intent.putExtra(Global.NISBI, matkul.nisbi)
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK)
                ctx.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return matkulViewHolder(
                    LayoutInflater.from(ctx).inflate(R.layout.activity_card_matkul, parent, false)
            )
        }
        return ubahMatkulViewHolder(
                LayoutInflater.from(ctx).inflate(R.layout.activity_ubah_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType === VIEW_TYPE_ONE) {
            (holder as matkulViewHolder).bind(position)
        } else {
            (holder as ubahMatkulViewHolder).bind(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}