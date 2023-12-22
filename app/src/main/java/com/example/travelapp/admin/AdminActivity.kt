package com.example.travelapp.admin
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.R
import com.example.travelapp.admin.AddTicketActivity
import com.example.travelapp.admin.KeretaAdminAdapter
import com.example.travelapp.authentication.AuthActivity
import com.example.travelapp.authentication.LoginFragment
import com.example.travelapp.databinding.ActivityAdminBinding
import com.example.travelapp.database.kereta.KeretaAdapter
import com.example.travelapp.database.kereta.dataKereta
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    private val db = FirebaseFirestore.getInstance()

    companion object {
        const val ADD_TICKET_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = LinearLayoutManager(this)
        binding.rvCardTicketAdmin.layoutManager = layoutManager

        // Button to add a new ticket
        binding.btnAdd.setOnClickListener {
            val addTicketIntent = Intent(this, AddTicketActivity::class.java)
            startActivityForResult(addTicketIntent, ADD_TICKET_REQUEST)
        }

        // Button to log out
        binding.btnLogout.setOnClickListener {
            val sharedPreferences = this.getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this, AuthActivity::class.java) // Replace AuthActivity with the correct activity that hosts LoginFragment
            startActivity(intent)
            finish()
        }

        // Get data from Firestore "dataKereta" collection
        fetchKeretaData()
    }

    private fun setupRecyclerView(keretaList: List<dataKereta>) {
        val keretaAdapter = KeretaAdminAdapter(this, ArrayList(keretaList))
        binding.rvCardTicketAdmin.adapter = keretaAdapter
    }

    private fun fetchKeretaData() {
        db.collection("dataKereta").get()
            .addOnSuccessListener { result ->
                Log.d("Firestore", "Success getting documents")
                val keretaList = result.documents.mapNotNull { it.toObject(dataKereta::class.java) }
                Log.d("Firestore", "All keretaList: $keretaList")

                // Create KeretaAdapter with the entire list
                setupRecyclerView(keretaList)
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting documents: ", exception)
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_TICKET_REQUEST && resultCode == RESULT_OK) {
            // Refresh data in AdminActivity, e.g., by re-fetching from Firestore
            fetchKeretaData()
        }
    }
}
