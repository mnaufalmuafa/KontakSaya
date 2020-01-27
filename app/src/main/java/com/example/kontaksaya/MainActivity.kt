package com.example.kontaksaya

import android.content.Intent
import android.net.sip.SipSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var RV : RecyclerView
    private val listKontak = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TAG","MainActivity OnCreate")

        RV = findViewById(R.id.RV)
        RV.setHasFixedSize(true)
        showRecyclerList()

        BtnTambahKontak.setOnClickListener {
            startActivity(Intent(this, TambahKontak::class.java))
        }
    }

    private fun showRecyclerList() {
        RV.layoutManager = LinearLayoutManager(this)

        while (listKontak.size > 0) {
            listKontak.removeAt(0)
        }

        var dr : DatabaseReference = FirebaseDatabase.getInstance().getReference()
        val Listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("BUANG","BUANG")
            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.d("BUANG","BUANG")
            }

        }
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val post = dataSnapshot.getValue(Contact::class.java)
                for (item : DataSnapshot in dataSnapshot.children) {
                    val contact : Contact = item.getValue(Contact::class.java)!!
                    listKontak.add(contact)
                }
                val RVContactAdapter = RVContactAdapter(listKontak,this@MainActivity)
                RV.adapter = RVContactAdapter
                dr.addValueEventListener(Listener)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "memuat daftar kontak:onCancelled", databaseError.toException())
                // ...
            }
        }
        dr.addValueEventListener(postListener)
    }

    override fun onStart() {
        Log.d("TAG","MainActivity OnStart1")
        super.onStart()
        Log.d("TAG","MainActivity OnStart2")
    }

    override fun onResume() {
        Log.d("TAG","MainActivity OnResume1")
        super.onResume()
        Log.d("TAG","MainActivity OnResume2")
    }

    override fun onPause() {
        Log.d("TAG","MainActivity OnPause1")
        super.onPause()
        Log.d("TAG","MainActivity OnPause2")
        while (listKontak.size > 0) {
            listKontak.removeAt(0)
        }

    }

    override fun onStop() {
        Log.d("TAG","MainActivity OnStop1")
        super.onStop()
        Log.d("TAG","MainActivity OnStop2")
    }

    override fun onDestroy() {
        Log.d("TAG","MainActivity OnDestroy1")
        super.onDestroy()
        Log.d("TAG","MainActivity OnDestroy2")
    }
}
