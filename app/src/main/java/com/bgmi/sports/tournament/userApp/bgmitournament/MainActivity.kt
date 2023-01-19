package com.bgmi.sports.tournament.userApp.bgmitournament

import android.content.Intent
import android.os.Build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.bgmi.sports.tournament.userApp.bgmitournament.Auth.RegisterUser
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true

            binding.drawerLayout.close()
            true
        }

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser!!


        settingUpUserProfileInHeaderView() //setting Up UserProfile In HeaderView when user not null



    }

    private fun settingUpUserProfileInHeaderView() {
        val headerView = binding.navigationView.getHeaderView(0)

        if(firebaseUser!=null){
            val userProfilePic=headerView.findViewById<ImageView>(R.id.userProfile)

            Picasso.get().load(firebaseUser.photoUrl).placeholder(getDrawable(R.drawable.user_profile)!!).into(userProfilePic)
        }
    }

    override fun onStart() {
        super.onStart()

        if (firebaseUser == null) {
            val intent = Intent(this, RegisterUser::class.java)
            startActivity(intent)
            finish()
        }
    }
}