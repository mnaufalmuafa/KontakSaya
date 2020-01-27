package com.example.kontaksaya

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_edit_contact.*


class EditContact : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    var lanjut : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        var extras : Bundle? = getIntent().extras
        val nama = extras?.getString("Nama")
        val NoTelp= extras?.getString("NoTelp")

        ETNama.setText(nama)
        ETNomorTelepon.setText(NoTelp)

        BtnFixUbah.setOnClickListener {
            ref = FirebaseDatabase.getInstance().getReference()
            val Listener = object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.d("BUANG","BUANG")
                    finish()
                }

                override fun onDataChange(p0: DataSnapshot) {
                    Log.d("BUANG","BUANG")
                    finish()
                }

            }
            val dataListener = object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (lanjut) {
                        if (p0.hasChild(ETNama.text.toString())) {
                            if (ETNama.text.toString().equals(nama)) {
                                var kontak : Contact = Contact(ETNama.text.toString(),ETNomorTelepon.text.toString())
                                ref.child(nama.toString()).setValue(kontak)
                                val returnIntent = Intent()
                                returnIntent.putExtra(getString(R.string.Result), ETNama.text.toString())
                                setResult(Activity.RESULT_OK, returnIntent)
                            }
                            else {
                                Log.d("TAG","Sudah ada kontak dengan nama ${ETNama.text.toString()}")
                                val text = "Sudah ada kontak dengan nama ${ETNama.text.toString()}"
                                val duration = Toast.LENGTH_LONG
                                val toast = Toast.makeText(applicationContext, text, duration)
                                toast.show()
                            }
                            ref.addValueEventListener(Listener)
                        }
                        else {
                            var kontak : Contact = Contact(ETNama.text.toString(),ETNomorTelepon.text.toString())
                            ref.child(nama.toString()).removeValue()
                            ref.child(ETNama.text.toString()).setValue(kontak)
                            val returnIntent = Intent()
                            returnIntent.putExtra(getString(R.string.Result), ETNama.text.toString())
                            setResult(Activity.RESULT_OK, returnIntent)
                            ref.addValueEventListener(Listener)
                        }
                        lanjut = false
                        finish()
                    }

                }

            }
            ref.addValueEventListener(dataListener)
        }
    }
}
