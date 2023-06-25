package com.beeldi.beelding

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.beeldi.beelding.entity.Equipement
import com.google.android.material.textview.MaterialTextView

class RecyclerAdapter(private var equipements: List<Equipement>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val itemName: MaterialTextView = itemView.findViewById(R.id.Name)
		val itemDomain: MaterialTextView = itemView.findViewById(R.id.Domain)
		val itemNbFaults: MaterialTextView = itemView.findViewById(R.id.nbFaults)
//		val itemPhoto: MaterialTextView = itemView.findViewById(R.id.Photo)

		init {
			Log.e("Tag init adapter", "adapter")
			itemView.setOnClickListener { v: View ->
				val position: Int = adapterPosition
				Toast.makeText(itemView.context, "You click here : ${position + 1}", Toast.LENGTH_SHORT).show()
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		Log.e("Tag view type", "isPresent")
		val v = LayoutInflater.from(parent.context).inflate(R.layout.equipement_item, parent, false)
		return ViewHolder(v)
	}

	override fun getItemCount(): Int {
		return equipements.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.itemName.text = equipements[position].name
		holder.itemDomain.text = equipements[position].domain
		holder.itemNbFaults.text = equipements[position].nbFaults.toString()
//		holder.itemPhoto.text = equipements[position].photo
	}
}