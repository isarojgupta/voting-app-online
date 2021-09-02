package io.android.voterapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import io.android.voterapp.databinding.ActivityLoginBinding.inflate
import io.android.voterapp.databinding.ActivityWelcomeBinding
import io.android.voterapp.databinding.ActivityWelcomeBinding.inflate

class WelcomeActivity : AppCompatActivity() {

    private lateinit var _binding:ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(_binding.root)


        _binding.welcomeLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        _binding.welcomeCreateAAccountBtn.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
            finish()
        }

    }
}