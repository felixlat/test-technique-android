package com.beeldi.beelding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.beeldi.beelding.entity.Equipement
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView


class RecyclerEquipementAdapter(private var equipements: List<Equipement>, val listEquipements: ListEquipements) : RecyclerView.Adapter<RecyclerEquipementAdapter.ViewHolder>() {
	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		val CardEquipement: CardView = itemView.findViewById(R.id.CardEquipement)

		val itemName: MaterialTextView = itemView.findViewById(R.id.Name)
		val itemDomain: MaterialTextView = itemView.findViewById(R.id.Domain)
		val itemNbFaults: MaterialTextView = itemView.findViewById(R.id.nbFaults)
		val itemPhoto: ShapeableImageView = itemView.findViewById(R.id.Photo)
		var equipement: Equipement? = null
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val v = LayoutInflater.from(parent.context).inflate(R.layout.equipement_item, parent, false)

		val viewHolder = ViewHolder(v)

		viewHolder.CardEquipement.setOnClickListener {
			var viewHolderEquipement = viewHolder.equipement
			if (viewHolderEquipement != null) {
				listEquipements.clickListener(viewHolderEquipement)
			}
		}
		return viewHolder
	}

	override fun getItemCount(): Int {
		return equipements.size
	}

	override fun onBindViewHolder(holder: RecyclerEquipementAdapter.ViewHolder, position: Int) {
		holder.itemName.text = equipements[position].name
		holder.itemDomain.text = equipements[position].domain
		holder.itemNbFaults.text = equipements[position].nbFaults.toString()
		holder.equipement = equipements[position]
		listEquipements.getImage(equipements[position].photo, holder.itemPhoto)
	}
}