package com.example.kontaksaya

import android.content.Intent
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

        var dr : DatabaseReference = FirebaseDatabase.getInstance().getReference()
        val listKontak = ArrayList<Contact>()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val post = dataSnapshot.getValue(Contact::class.java)
                for (item : DataSnapshot in dataSnapshot.children) {
                    val contact : Contact = item.getValue(Contact::class.java)!!
                    listKontak.add(contact)
                }
                val RVContactAdapter = RVContactAdapter(listKontak)
                RV.adapter = RVContactAdapter;
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
        Log.d("TAG","MainActivity OnCreate")
        super.onStart()
        Log.d("TAG","MainActivity OnCreate")
    }

    override fun onResume() {
        Log.d("TAG","MainActivity OnCreate")
        super.onResume()
        Log.d("TAG","MainActivity OnCreate")
    }

    override fun onPause() {
        Log.d("TAG","MainActivity OnCreate")
        super.onPause()
        Log.d("TAG","MainActivity OnCreate")
    }

    override fun onStop() {
        Log.d("TAG","MainActivity OnCreate")
        super.onStop()
        Log.d("TAG","MainActivity OnCreate")
    }

    override fun onDestroy() {
        Log.d("TAG","MainActivity OnCreate")
        super.onDestroy()
        Log.d("TAG","MainActivity OnCreate")
    }
}
