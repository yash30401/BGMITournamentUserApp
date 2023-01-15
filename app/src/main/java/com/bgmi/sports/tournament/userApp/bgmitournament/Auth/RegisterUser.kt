package com.bgmi.sports.tournament.userApp.bgmitournament.Auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bgmi.sports.tournament.userApp.bgmitournament.R
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.ActivityRegisterUserBinding

class RegisterUser : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}