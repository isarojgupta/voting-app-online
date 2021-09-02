package io.android.voterapp

import Extenstions.toast
import FirebaseUtils.firebaseAuth
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.android.voterapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.loginCreateAAccountBtn.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
            finish()
        }
        _binding.loginBtn.setOnClickListener {
            signIn()
        }

        //reset password
        _binding.resetPassword.setOnClickListener {
            if(_binding.emailLogin.text.toString().isEmpty()){
                toast("Please Enter email address on which reset link will be sent!")
            }else {
                val emailAddress = _binding.emailLogin.text.toString()

                Firebase.auth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "Email sent.")
                        }
                    }
             }
            }


    }

    private fun signIn() {

        val email = _binding.emailLogin.text.toString()
        val password = _binding.loginPassword.text.toString()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Log.d(TAG, "signInWithEmail:success")


                    val user = Firebase.auth.currentUser


                    startActivity(Intent(this,CandidateActivity::class.java))
                    finish()
                } else {

                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                   toast("Authentication failed")

                }
            }

    }


    companion object{
        private const val TAG = "LoginActivity"
    }
}