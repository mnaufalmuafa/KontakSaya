package com.example.kontaksaya

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class RVContactAdapter(private val listContact : ArrayList<Contact>, private val mContext : Context)
    : RecyclerView.Adapter<RVContactAdapter.ContactViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_kontak, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listContact.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val Contact = listContact[position]
        holder.TVNama.text = Contact.nama
        holder.TVNomor.text = Contact.NoTelp
        holder.IVICCopy.setOnClickListener {
            val clipboard = mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("simple text", "Hello, World!")
        }
        holder.IVICTrash.setOnClickListener{
            var dr : DatabaseReference = FirebaseDatabase.getInstance().getReference()
            dr.child(holder.TVNama.text.toString()).removeValue()
        }
        holder.CV.setOnClickListener{
            val intent : Intent = Intent(mContext, ContactDetail::class.java)
            intent.putExtra("Nama",holder.TVNama.text.toString())
            intent.putExtra("NoTelp",holder.TVNomor.text.toString())
            mContext.startActivity(intent)
        }
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var TVNama : TextView = itemView.findViewById(R.id.TVNama)
        var TVNomor : TextView = itemView.findViewById(R.id.TVNomor)
        var IVICCopy : ImageView = itemView.findViewById(R.id.IVICCopy)
        var IVICTrash : ImageView = itemView.findViewById(R.id.IVICTrash)
        var CV : CardView = itemView.findViewById(R.id.CV)
    }
}