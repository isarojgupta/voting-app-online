package io.android.voterapp

import Extenstions.toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.android.voterapp.databinding.ActivityCandidateBinding
import java.util.ArrayList

class CandidateActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityCandidateBinding
    private lateinit var party:String
    private lateinit var name:String
    private lateinit var profilePicture:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding  = ActivityCandidateBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.candidateRecycler.layoutManager = LinearLayoutManager(this)

        val db: FirebaseFirestore = Firebase.firestore
            val data = ArrayList<CandidateModel>()
       db.collection("election_condidate")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    party = document.data.getValue("Party").toString()
                    name = document.data.getValue("name").toString()
                    profilePicture = document.data.getValue("profile_picture").toString()

                    toast("party name: $party \nname: $name \nprofile photo : $profilePicture")
                        Log.d(TAG,
                            "party name: $party \nname: $name \nprofile photo : $profilePicture"
                        )

                    data.add(CandidateModel(name,party,profilePicture))

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        Log.d(TAG, "DATA of details: $data")
        // This will pass the ArrayList to our Adapter
        val adapter = CandidateAdaptor(data)

        // Setting the Adapter with the recyclerview
        _binding.candidateRecycler.adapter = adapter

    }

    companion object{
        private const val TAG = "CandidateActivity"
    }
}