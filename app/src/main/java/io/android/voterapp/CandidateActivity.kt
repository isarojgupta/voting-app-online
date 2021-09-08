package io.android.voterapp

import Extenstions.toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.android.voterapp.databinding.ActivityCandidateBinding
import java.util.ArrayList

class CandidateActivity : AppCompatActivity() {
    private lateinit var viewModel: CandidateViewModel
    private lateinit var _binding:ActivityCandidateBinding
    private val rootRef = FirebaseFirestore.getInstance()
    private val query  = rootRef!!.collection("election_condidate")
    //val options = FirestoreRecyclerOptions.Builder<CandidateModel>().setQuery(query, CandidateModel::class.java).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding  = ActivityCandidateBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        viewModel = ViewModelProvider(this).get(CandidateViewModel::class.java)
        val db = Firebase.firestore
        _binding.candidateRecycler.layoutManager = LinearLayoutManager(this)

//        getResponseUsingCallback()

        val data = HashMap<String,String>()
          db.collection("election_condidate")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }




//        val adapter = CandidateAdaptor(data)
//        // Setting the Adapter with the recyclerview
//        _binding.candidateRecycler.adapter = adapter

    }
//    private fun getResponseUsingCallback() {
//        viewModel.getResponseUsingCallback(object : FirebaseCallback {
//            override fun onResponse(response: Response) {
//               val adaptor = response.candidates?.let { CandidateAdaptor(it) }
//                _binding.candidateRecycler.adapter = adaptor
//                Log.d(TAG, "all the data: $response")
//                print(response)
//            }
//        })
//    }

    companion object{
        private const val TAG = "CandidateActivity"
    }
}





