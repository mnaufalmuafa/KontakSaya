package com.example.kontaksaya

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_contact_detail.*
import kotlinx.android.synthetic.main.item_kontak.TVNama


class ContactDetail : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    var lanjut : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        var extras : Bundle? = getIntent().extras
        val nama = extras?.getString("Nama")
        val NoTelp = extras?.getString("NoTelp")

        TVNama.text = nama
        TVNoTelp.text = NoTelp

        BtnUbah.setOnClickListener {
            val intent : Intent = Intent(this, EditContact::class.java)
            intent.putExtra("Nama",TVNama.text.toString())
            intent.putExtra("NoTelp",TVNoTelp.text.toString())
            startActivityForResult(intent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                //val result: String = attr.data.getStringExtra("result")
                val result: String? = data?.getStringExtra("result")
                Log.d("TAG","Nama setelah diubah : $result")
                ref = FirebaseDatabase.getInstance().getReference()
                val Listener = object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        Log.d("BUANG","BUANG")
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        Log.d("TAGA","Kesini dong")
                        TVNama.setText(p0.child("${result}").child("nama").getValue(String::class.java))
                        TVNoTelp.setText(p0.child("${result}").child("noTelp").getValue(String::class.java))
                    }

                }
                ref.addValueEventListener(Listener)
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Log.d("TAG","No result from Edit Contact Activity")
            }
        }
    }
}
