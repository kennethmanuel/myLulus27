package com.nmp.myapplication

object Global {
    val nisbi:Array<Nisbi> = arrayOf(
        Nisbi(1, "A"),
        Nisbi(2, "AB"),
        Nisbi(3, "B"),
        Nisbi(4, "BC"),
        Nisbi(5, "C"),
        Nisbi(6, "D"),
        Nisbi(7, "E")
    )
    var matkuls:ArrayList<MataKuliah> = ArrayList()
    var mahasiswa_ambil_mks:ArrayList<MhsAmbilMk> = ArrayList()
    var nrp:String = ""
    var nama:String = ""
    var angkatan:Int = 0
    val KODE_MATKUL = "KODE_MATKUL"
    val NAMA_MATKUL = "NAMA_MATKUL"
    val SKS_MATKUL = "SKS_MATKUL"
    var ipk: Double = 0.0
    var totalSks: Int =0
    var totalNilaiD: Int = 0
}