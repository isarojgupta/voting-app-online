package io.android.voterapp

import Extenstions.toast
import FirebaseUtils.firebaseUser
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.android.voterapp.databinding.ActivityProfileBinding
import android.provider.MediaStore

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.io.IOException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


open class ProfileActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityProfileBinding

    private val PICK_IMAGE = 123
    private lateinit var imagePath:Uri
    private lateinit var  databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.submitProfile.setOnClickListener{
            uploadProfileDetailsToDb()

        }
        databaseReference = FirebaseDatabase.getInstance().reference

        _binding.userPhoto.setOnClickListener {
            val profileIntent = Intent()
            profileIntent.type = "image/*"
            profileIntent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(profileIntent, "Select Image."), PICK_IMAGE)

        }

    }

    //    protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent):Void {
//        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.data != null) {
//            imagePath = data.data!!
//            try {
//                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imagePath)
//                _binding.userPhoto.setImageBitmap(bitmap)
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data)
//    }
    private fun uploadProfileDetailsToDb(){
        val fullName = _binding.fullName.text.toString()
        val phoneNumber = _binding.phoneNumber.text.toString()
        val adharNumber = _binding.adharCard.text.toString()

         var userInformation = UserInformation(fullName,phoneNumber,adharNumber)
        val db = Firebase.firestore
        db.collection("users")
            .add(userInformation)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG,"Document added with ID: ${documentReference.id}")
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
    companion object{
        private const val TAG = "ProfileActivity"
    }
}