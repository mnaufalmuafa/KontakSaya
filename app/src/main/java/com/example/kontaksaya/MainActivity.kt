package com.example.kontaksaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var RV : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RV = findViewById(R.id.RV)
        RV.setHasFixedSize(true)
        showRecyclerList()

        BtnTambahKontak.setOnClickListener {
            startActivity(Intent(this, TambahKontak::class.java))
        }
    }

    private fun showRecyclerList() {
        RV.layoutManager = LinearLayoutManager(this)
        val listKontak = ArrayList<Contact>();
        listKontak.add(Contact("gs","098"))
        listKontak.add(Contact("ss","099"))
        val RVContactAdapter = RVContactAdapter(listKontak)
        RV.adapter = RVContactAdapter;
    }
}
