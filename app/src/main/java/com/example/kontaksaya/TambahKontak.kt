package com.example.kontaksaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_tambah_kontak.*

class TambahKontak : AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_kontak)

        ref = FirebaseDatabase.getInstance().getReference()



        BtnTambah.setOnClickListener {
            var Nama = ETNama.text
            var NoTelp = ETNomorTelepon.text
            Log.d("TAG","Nama : "+Nama)
            Log.d("TAG","No Telp : "+NoTelp)
        }
    }
}
