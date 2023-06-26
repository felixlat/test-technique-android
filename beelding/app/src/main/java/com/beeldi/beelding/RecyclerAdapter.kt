package com.beeldi.beelding

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.beeldi.beelding.entity.Equipement
import com.google.android.material.textview.MaterialTextView


class RecyclerAdapter(private var equipements: List<Equipement>, val listEquipements: ListEquipements) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		val CardEquipement: CardView = itemView.findViewById(R.id.CardEquipement)

		val itemName: MaterialTextView = itemView.findViewById(R.id.Name)
		val itemDomain: MaterialTextView = itemView.findViewById(R.id.Domain)
		val itemNbFaults: MaterialTextView = itemView.findViewById(R.id.nbFaults)
//		val itemPhoto: MaterialTextView = itemView.findViewById(R.id.Photo)

		var equipement: Equipement? = null

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
//		ViewHolder(v, activity).CardEquipement.setOnClickListener {
//			val bundle = Bundle()
//			bundle.putInt("position", position)
//
//			val intent = Intent(activity, DetailsActivity::class.java)
//			intent.putExtra("name", equipements[position].name)
//			intent.putExtra("domain", equipements[position].domain)
//			intent.putExtra("nbFaults", equipements[position].nbFaults)
//			intent.putExtra("key", equipements[position].key)
//
//			activity.startActivity(intent.putExtras(bundle))
//		}

		val viewHolder =  ViewHolder(v)

		viewHolder.CardEquipement.setOnClickListener{
			var viewHolderEquipement = viewHolder.equipement
			if(viewHolderEquipement != null) {
				listEquipements.clickListener(viewHolderEquipement)
			}
		}

		return viewHolder
	}

	override fun getItemCount(): Int {
		return equipements.size
	}

	override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
		holder.itemName.text = equipements[position].name
		holder.itemDomain.text = equipements[position].domain
		holder.itemNbFaults.text = equipements[position].nbFaults.toString()
		holder.equipement = equipements[position]
//		holder.itemPhoto.text = equipements[position].photo
	}
}