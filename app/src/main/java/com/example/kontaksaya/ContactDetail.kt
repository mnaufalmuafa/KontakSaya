package com.example.kontaksaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contact_detail.*
import kotlinx.android.synthetic.main.item_kontak.*
import kotlinx.android.synthetic.main.item_kontak.TVNama

class ContactDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        var extras : Bundle? = getIntent().extras
        val nama = extras?.getString("Nama")
        val NoTelp = extras?.getString("NoTelp")

        TVNama.text = nama
        TVNoTelp.text = NoTelp
    }
}
