package com.example.simon.data

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class ScoreOnline(val name: String, val score: Int)

fun getTop10UsersFromFirebase(level: String, context: Context, callback: (List<ScoreOnline>) -> Unit) {

    val databaseReference = FirebaseDatabase.getInstance().getReference("scores/$level")
    val auth = FirebaseDatabase.getInstance()

    databaseReference.orderByChild("score").limitToLast(10).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listScores = mutableListOf<ScoreOnline>()
                for (scoreSnapshot in dataSnapshot.children) {
                    val email = scoreSnapshot.child("email").getValue(String::class.java)
                    val score = scoreSnapshot.child("score").getValue(Int::class.java) ?: 0

                    //val user = auth.getUser(userId) // Cette méthode est hypothétique et doit être remplacée par la méthode appropriée de récupération de l'utilisateur
                    //val name = user?.displayName ?: "Unknown"

                    if (email != null) {
                        listScores.add(ScoreOnline(email, score))
                    }
                }
                // Tri des scores par ordre décroissant
                listScores.sortByDescending { it.score }
                callback(listScores)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(ContentValues.TAG, "Ecriture des donnees sur FireBase a echoue !")
                Toast.makeText(context,"Erreur de lecture. Veuillez vérifier votre connexion sinon réessayez ultérieurement", Toast.LENGTH_SHORT,).show()
            }
        })



}