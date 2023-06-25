package com.beeldi.beelding

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beeldi.beelding.entity.Equipement
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private var listEquipments = mutableListOf<Equipement>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.rvEquipement)
        recyclerview.layoutManager = LinearLayoutManager(this)
        retrieveData(recyclerview)
    }

    private fun retrieveData(recyclerview: RecyclerView) {
        val database = Firebase.database
        val equipmentsDatabaseReference = database.getReference("Equipments")
        equipmentsDatabaseReference.addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val equipements = snapshot.children

                equipements.forEach {
                    try {
                        val data: HashMap<String, Any> = it.value as HashMap<String, Any>
                        val name = data["name"] as String
                        val domain = data["domain"] as String
                        val nbFaults = data["nbFaults"] as Long
                        val photo = data["photo"] as String

                        val equipement = Equipement(name, domain, nbFaults, photo)

//						addEquipement(equipement)
                        listEquipments.add(equipement)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                recyclerview.adapter = RecyclerAdapter(listEquipments)
                Log.e("Tag adapter", "${recyclerview.adapter}")
                Log.e("Tag list size onCreate", "${listEquipments.size}")

                listEquipments.forEach {
                    Log.e("Tag equipement", "${it.name}")
                }
                Log.d(TAG, snapshot.toString())
            }
        })
    }

    companion object {
        const val TAG = "MainActivity"
    }
}