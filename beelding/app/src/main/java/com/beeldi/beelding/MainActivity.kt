package com.beeldi.beelding

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beeldi.beelding.entity.Equipement
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), ListEquipements {

	private var listEquipments = mutableListOf<Equipement>()

	@SuppressLint("MissingInflatedId")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val recyclerview = findViewById<RecyclerView>(R.id.rvEquipement)
		val CardEquipement = findViewById<CardView>(R.id.CardEquipement)

		retrieveData(recyclerview, this@MainActivity)
	}

	private fun retrieveData(recyclerview: RecyclerView, activity: Activity) {
		val database = Firebase.database
		Log.e("Tag retrieveData", "$database")
		val equipmentsDatabaseReference = database.getReference("Equipments")

		equipmentsDatabaseReference.addListenerForSingleValueEvent(object :
			ValueEventListener {
			override fun onCancelled(error: DatabaseError) {
				Log.e("Tag onCancelled", "onCancelled")
				Log.d(TAG, error.message)
			}

			override fun onDataChange(snapshot: DataSnapshot) {
				Log.e("Tag onDataChange", "onDataChange")
				val equipements = snapshot.children
				equipements.forEach {
					try {
						Log.e("Tag equipement", "$it")

						val data: HashMap<String, Any> = it.value as HashMap<String, Any>
						val key = it.key
						val name = data["name"] as String
						val domain = data["domain"] as String
						val nbFaults = data["nbFaults"] as Long
						val photo = data["photo"] as String

//						val notes = data["notes"] as String
//						val quantity = data["quantity"] as Long
//						val serialNumber = data["serialNumber"] as Long
//						val niveau = data["niveau"] as String
//						val building = data["building"] as String
//						val local = data["local"] as String
//						val station = data["station"] as String
//						val model = data["model"] as String
//						val brand = data["brand"] as String
//						val status = data["status"] as String

						val equipement = Equipement(key, name, domain, nbFaults, photo/*, notes, quantity, serialNumber, niveau, building, local, station, model, brand, status*/)
						listEquipments.add(equipement)
					} catch (e: Exception) {
						e.printStackTrace()
					}
				}

				recyclerview.layoutManager = LinearLayoutManager(activity)
				recyclerview.adapter = RecyclerEquipementAdapter(listEquipments, this@MainActivity)

				Log.d(TAG, snapshot.toString())
			}
		})
	}

	companion object {
		const val TAG = "MainActivity"
	}

	override fun clickListener(equipement: Equipement) {
			val bundle = Bundle()

			val intent = Intent(this@MainActivity, DetailsActivity::class.java)
			intent.putExtra("name", equipement.name)
			intent.putExtra("domain", equipement.domain)
			intent.putExtra("nbFaults", equipement.nbFaults)
			intent.putExtra("key", equipement.key)

			this@MainActivity.startActivity(intent.putExtras(bundle))
		}
}