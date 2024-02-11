package com.example.simon.data

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

data class DataScore(
    val userId: String?,
    val score: Int?,
    val email: String?
)

/*fun envoieScoreEnLigne(databaseReference: DatabaseReference, dataScore: DataScore, context: Context) {
    // Envoi des données à la base de données Firebase
    /*val scoreMap = mapOf(
        "userId" to dataScore.userId,
        "score" to dataScore.score
    )*/
    //val value = ""+dataScore.userId+dataScore.score

    databaseReference.push().setValue(dataScore)
        .addOnSuccessListener {
            // Gérer le succès de l'écriture
            Log.d(ContentValues.TAG, "Ecriture des donnees sur FireBase reussi !") //affichage journal
        }
        .addOnFailureListener {
            // Gérer l'échec de l'écriture
            Log.w(ContentValues.TAG, "Ecriture des donnees sur FireBase a echoue !")
            Toast.makeText(context,"Erreur de l'écriture. Veuillez vérifier votre connexion sinon réessayez ultérieurement", Toast.LENGTH_SHORT,).show()
        }
}*/

fun envoieScoreEnLigne(databaseReference: DatabaseReference, dataScore: DataScore, context: Context) {
    val userScoreRef = databaseReference.child(dataScore.userId!!)

    userScoreRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val currentHighScore = snapshot.child("score").getValue(Int::class.java) ?: 0
            if (dataScore.score!! > currentHighScore) {
                // Le nouveau score est plus élevé, mettre à jour la base de données
                userScoreRef.setValue(dataScore).addOnSuccessListener {
                    Log.d(ContentValues.TAG, "Nouveau score le plus élevé enregistré avec succès !")
                }.addOnFailureListener {
                    Log.w(ContentValues.TAG, "Echec de l'enregistrement du nouveau score le plus élevé.")
                    Toast.makeText(context, "Erreur lors de l'enregistrement du score. Veuillez vérifier votre connexion sinon réessayez ultérieurement.", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Le score actuel n'est pas plus élevé, ne pas mettre à jour
                Log.d(ContentValues.TAG, "Le score actuel n'est pas plus élevé que le score le plus élevé.")
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Gérer l'erreur
            Log.w(ContentValues.TAG, "Erreur de récupération de données.", databaseError.toException())
        }
    })
}

