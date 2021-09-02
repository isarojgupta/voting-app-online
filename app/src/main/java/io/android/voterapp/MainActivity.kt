package io.android.voterapp

import FirebaseUtils.firebaseUser
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.android.voterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)




    }
}