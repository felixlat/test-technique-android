package com.beeldi.beelding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beeldi.beelding.entity.Checkpoint
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class RecyclerCheckpointAdapter(private var checkpoints: List<Checkpoint>, val listCheckpoints: ListCheckpoints) : RecyclerView.Adapter<RecyclerCheckpointAdapter.ViewHolder>() {

	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
		val checkpointName: MaterialTextView = itemView.findViewById(R.id.checkpointName)
		val faultCheckpoint: MaterialTextView = itemView.findViewById(R.id.faultCheckpoint)
		val recommandation: MaterialTextView = itemView.findViewById(R.id.recommandation)

		val checkpointPhoto: ShapeableImageView = itemView.findViewById(R.id.checkpointPhoto)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val v = LayoutInflater.from(parent.context).inflate(R.layout.checkpoint_item, parent, false)
		return ViewHolder(v)
	}

	override fun getItemCount(): Int {
		return checkpoints.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.checkpointName.text = checkpoints[position].name
		holder.faultCheckpoint.text = checkpoints[position].fault
		holder.recommandation.text = checkpoints[position].recommandation
//		holder.checkpointPhoto.text = checkpoints[position].photo
		listCheckpoints.getImageCheckpoint(checkpoints[position].photo, holder.checkpointPhoto)
	}
}