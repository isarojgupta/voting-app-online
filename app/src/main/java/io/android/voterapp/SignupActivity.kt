package io.android.voterapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.android.voterapp.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var _binding:ActivitySignupBinding

    lateinit var mDatabase : DatabaseReference
    var mAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _binding.signUpLogin.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        _binding.createAcntBtn.setOnClickListener {
            registerUser()
        }

    }

    private fun registerUser () {


        var email = _binding.emailAddress.text.toString()
        var password = _binding.passwordSignup.text.toString()


        if (email.isNotEmpty() && password.isNotEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {

                    startActivity(Intent(this, ProfileActivity::class.java))
                    Toast.makeText(this, "Successfully registered :)", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this, "Error registering, try again later :(", Toast.LENGTH_LONG).show()
                }
            })
        }else {
            Toast.makeText(this,"Please fill up the Credentials :|", Toast.LENGTH_LONG).show()
        }
    }
}