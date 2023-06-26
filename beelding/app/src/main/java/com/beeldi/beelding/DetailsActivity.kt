package com.beeldi.beelding

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beeldi.beelding.entity.Checkpoint
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DetailsActivity : AppCompatActivity(), ListCheckpoints {
	private var listCheckpoints = mutableListOf<Checkpoint>()

	@SuppressLint("MissingInflatedId")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.equipement_details)

		val nameDetail: TextView = findViewById(R.id.nameDetail)
		val domainDetail: TextView = findViewById(R.id.domainDetail)
		val nbFaultsDetail: TextView = findViewById(R.id.nbFaultsDetail)

		val bundle: Bundle? = intent.extras

		bundle ?: return

		val key = bundle.getString("key")
		Log.e("Tag key", "$key")

		val name = bundle.getString("name")
		val domain = bundle.getString("domain")
		val nbFault = bundle.getString("nbFault")

		nameDetail.text = name
		domainDetail.text = domain
		nbFaultsDetail.text = nbFault


		val recyclerview = findViewById<RecyclerView>(R.id.rvCheckpoint)

		retrieveData(key, recyclerview)
	}

	private fun retrieveData(key: String?, recyclerview: RecyclerView) {
		val database = Firebase.database
		val equipmentsDatabaseReference = database.getReference("Checkpoints")
		equipmentsDatabaseReference.addListenerForSingleValueEvent(object :
			ValueEventListener {
			override fun onCancelled(error: DatabaseError) {
				Log.d(MainActivity.TAG, error.message)
			}

			override fun onDataChange(snapshot: DataSnapshot) {
				val checkpoints = snapshot.children
				checkpoints.forEach {
					try {
						Log.e("Tag checkpoints", "$it")

						val data: HashMap<String, Any> = it.value as HashMap<String, Any>

						val equipmentKey = data["equipmentKey"] as String
						val recommandation = data["recommandation"] as String
						val name = data["name"] as String
						val fault = data["fault"] as String
						val photo = if (data["photo"] != null) data["photo"] as String else null

						val checkpoint = Checkpoint(equipmentKey, recommandation, name, fault, photo)

						if (checkpoint.equipmentKey == key) {
							listCheckpoints.add(checkpoint)
						}

					} catch (e: Exception) {
						e.printStackTrace()
					}
				}

				recyclerview.layoutManager = LinearLayoutManager(this@DetailsActivity)
				recyclerview.adapter = RecyclerCheckpointAdapter(listCheckpoints, this@DetailsActivity)

				Log.d(MainActivity.TAG, snapshot.toString())
			}
		})
	}

	companion object {
		const val TAG = "DetailsActivity"
	}

	override fun getImageCheckpoint(url: String?, photoGlide: ShapeableImageView) {
		Glide.with(this@DetailsActivity)
			.load(url)
			.into(photoGlide)
	}
}