package com.bgmi.sports.tournament.userApp.bgmitournament

import android.content.Intent
import android.os.Build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bgmi.sports.tournament.userApp.bgmitournament.Auth.LoginUser
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

            when(menuItem.itemId){
                R.id.profile->{
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                }
                R.id.orders->{
                    Toast.makeText(this, "Orders",Toast.LENGTH_SHORT).show()
                }
                R.id.conatctUs->{
                    Toast.makeText(this, "Contact Us", Toast.LENGTH_SHORT).show()
                }
                R.id.aboutUs->{
                    Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show()
                }
                R.id.help->{
                    Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show()
                }
            }

            binding.drawerLayout.close()
            true
        }

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser!!


        settingUpUserProfileInHeaderView() //setting Up UserProfile In HeaderView when user not null


    }

    private fun settingUpUserProfileInHeaderView() {
        val headerView = binding.navigationView.getHeaderView(0)

        if (firebaseUser != null) {
            val userProfilePic = headerView.findViewById<ImageView>(R.id.userProfile)
            Picasso.get().load(firebaseUser.photoUrl)
                .placeholder(getDrawable(R.drawable.user_profile)!!).into(userProfilePic)

            val username = headerView.findViewById<TextView>(R.id.username)
            val useremail = headerView.findViewById<TextView>(R.id.userEmail)

            username.text = firebaseUser.displayName
            useremail.text =firebaseUser.email

            val logoutbtn=headerView.findViewById<android.widget.Button>(R.id.btnLogout)

            logoutbtn.setOnClickListener {
                firebaseAuth.signOut()
                val intent=Intent(this@MainActivity,LoginUser::class.java)
                startActivity(intent)
                finish()
            }

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