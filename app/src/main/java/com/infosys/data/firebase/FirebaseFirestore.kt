package com.infosys.data.firebase

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.infosys.data.model.user.User
import kotlinx.coroutines.flow.MutableStateFlow

object FirebaseFirestore {
    val userInfo = MutableStateFlow<User?>(null)

    fun addUserInfo(user: User) {
        Firebase.firestore.collection("user")
            .document(user.id)
            .set(user)
    }

    fun fetchUserDetailsByEmail(email: String) {
        Firebase.firestore.collection("user")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    for (document in querySnapshot) {
                        val user = document.toObject(User::class.java)
                        println("User found: $user")
                        userInfo.value = user
                    }
                } else {
                    println("No user found with the provided email")
                }
            }
            .addOnFailureListener { exception ->
                println("Error fetching user: ${exception.localizedMessage}")
            }
    }
}