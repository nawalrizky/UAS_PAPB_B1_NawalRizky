package com.example.travelapp.database

import com.example.travelapp.authentication.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


object FirebaseUtil {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun getUsername(userId: String, onUsernameReceived: (String?) -> Unit) {
        val userRef = database.child("users").child(userId)
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userName = dataSnapshot.child("username").getValue(String::class.java)
                onUsernameReceived(userName)
            }

            override fun onCancelled(error: DatabaseError) {
                onUsernameReceived(null)
            }
        })
    }
    fun getUserData(userId: String, onUserDataReceived: (UserData?) -> Unit) {
        val userRef = database.child("users").child(userId)
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val username = dataSnapshot.child("username").getValue(String::class.java)
                val email = dataSnapshot.child("email").getValue(String::class.java)
                val userData = UserData(username, email)
                onUserDataReceived(userData)
            }

            override fun onCancelled(error: DatabaseError) {
                onUserDataReceived(null)
            }
        })
    }
}
