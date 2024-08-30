package com.example.mdindiatask

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class HospitalAdapter(private val hospitalList: List<Hospital>) : RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>() {

    inner class HospitalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.hospital_name)
        val addressTextView: TextView = view.findViewById(R.id.hospital_address)
        val pinCodeTextView: TextView = view.findViewById(R.id.hospital_pincode)
        val contactTextView: TextView = view.findViewById(R.id.hospital_contact)
        val mapIcon: ImageView = view.findViewById(R.id.map_icon)
        val cardView: CardView = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hospital_card, parent, false)
        return HospitalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val hospital = hospitalList[position]
        holder.nameTextView.text = hospital.HospitalName
        holder.addressTextView.text = hospital.HospitalAddress
        holder.pinCodeTextView.text = hospital.PinCode
        holder.contactTextView.text = hospital.Contact_Mobile_No ?: "N/A"

        // Set alternating background colors
        when (position % 3) {
            0 -> holder.cardView.setCardBackgroundColor(Color.RED)
            1 -> holder.cardView.setCardBackgroundColor(Color.GREEN)
            2 -> holder.cardView.setCardBackgroundColor(Color.BLUE)
        }

        // Open Google Maps when map icon is clicked
        holder.mapIcon.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:${hospital.Latitude},${hospital.Longitude}?q=${hospital.HospitalName}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            holder.itemView.context.startActivity(mapIntent)
        }
    }

    override fun getItemCount(): Int = hospitalList.size
}
