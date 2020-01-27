package com.example.kontaksaya

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_edit_contact.*
import kotlinx.android.synthetic.main.activity_tambah_kontak.*
import kotlinx.android.synthetic.main.activity_tambah_kontak.ETNama
import kotlinx.android.synthetic.main.activity_tambah_kontak.ETNomorTelepon

class TambahKontak : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    var lanjut : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_kontak)
        BtnTambah.setOnClickListener {
            var Nama = ETNama.text
            var NoTelp = ETNomorTelepon.text
            if (!Nama.isEmpty() && !NoTelp.isEmpty()) {
                ref = FirebaseDatabase.getInstance().getReference()
                val dataListener = object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if (lanjut) {

                            if (!p0.hasChild(Nama.toString())) {
                                var kontak : Contact = Contact(Nama.toString(),NoTelp.toString())
                                ref.child(ETNama.text.toString()).setValue(kontak)
                            }
                            lanjut = false
                        }
                    }

                }
                ref.addValueEventListener(dataListener)
                finish()
            }
            finish()
        }
    }
}
