package com.example.kontaksaya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVContactAdapter(private val listContact : ArrayList<Contact>)
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
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var TVNama : TextView = itemView.findViewById(R.id.TVNama);
        var TVNomor : TextView = itemView.findViewById(R.id.TVNomor);
    }
}