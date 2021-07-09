package com.beeldi.beelding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrieveData()
    }



    private fun retrieveData() {
        val database = Firebase.database
        val equipmentsDatabaseReference = database.getReference("Equipments")
        equipmentsDatabaseReference.addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
               Log.d(TAG, error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, snapshot.toString())
            }
        })
    }

    companion object {
        const val TAG = "MainActivity"
    }
}