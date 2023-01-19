package com.bgmi.sports.tournament.userApp.bgmitournament

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bgmi.sports.tournament.userApp.bgmitournament.Auth.RegisterUser
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()



        firebaseAuth=FirebaseAuth.getInstance()
        firebaseUser=firebaseAuth.currentUser!!


    }

    override fun onStart() {
        super.onStart()

        if(firebaseUser==null){
            val intent=Intent(this,RegisterUser::class.java)
            startActivity(intent)
            finish()
        }
    }
}