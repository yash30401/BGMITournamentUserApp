package com.bgmi.sports.tournament.userApp.bgmitournament

import android.content.Intent
import android.os.Build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bgmi.sports.tournament.userApp.bgmitournament.Auth.LoginUser
import com.bgmi.sports.tournament.userApp.bgmitournament.Auth.RegisterUser
import com.bgmi.sports.tournament.userApp.bgmitournament.Tickets.Afternoon
import com.bgmi.sports.tournament.userApp.bgmitournament.Tickets.Evening
import com.bgmi.sports.tournament.userApp.bgmitournament.Tickets.Morning
import com.bgmi.sports.tournament.userApp.bgmitournament.Tickets.MyOrders
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: FragmentManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragment=supportFragmentManager

        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true

            clickItem(menuItem.itemId) //Callling Funtion When Menu Item clicks

            binding.drawerLayout.close()
            true
        }

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser!!


        settingUpUserProfileInHeaderView() //setting Up UserProfile In HeaderView when user not null

        binding.morningMatches.setOnClickListener {
            clickCardView(binding.morningMatches.id)
        }

        binding.afternoonMatches.setOnClickListener {
            clickCardView(binding.afternoonMatches.id)
        }
        binding.eveningMatches.setOnClickListener {
            clickCardView(binding.eveningMatches.id)
        }
        binding.myOrders.setOnClickListener {
            clickCardView(binding.myOrders.id)
        }


    }

    private fun clickCardView(id: Int) {

        when(id){
            R.id.morningMatches->{
                fragment.beginTransaction().replace(R.id.drawerLayout,Morning()).addToBackStack("home").commit()
            }
            R.id.afternoonMatches->{
                fragment.beginTransaction().replace(R.id.drawerLayout,Afternoon()).addToBackStack("home").commit()
            }
            R.id.eveningMatches->{
                fragment.beginTransaction().replace(R.id.drawerLayout,Evening()).addToBackStack("home").commit()
            }
            R.id.myOrders->{
                fragment.beginTransaction().replace(R.id.drawerLayout,MyOrders()).addToBackStack("home").commit()
            }
        }
    }

    private fun clickItem(itemId: Int) {
        when (itemId) {
            R.id.profile -> {
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
            }
            R.id.orders -> {
                Toast.makeText(this, "Orders", Toast.LENGTH_SHORT).show()
            }
            R.id.conatctUs -> {
                Toast.makeText(this, "Contact Us", Toast.LENGTH_SHORT).show()
            }
            R.id.aboutUs -> {
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show()
            }
            R.id.help -> {
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show()
            }
        }
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
            useremail.text = firebaseUser.email

            val logoutbtn = headerView.findViewById<android.widget.Button>(R.id.btnLogout)

            logoutbtn.setOnClickListener {
                firebaseAuth.signOut()
                val intent = Intent(this@MainActivity, LoginUser::class.java)
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