package com.bgmi.sports.tournament.userApp.bgmitournament.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bgmi.sports.tournament.userApp.bgmitournament.ViewModel.AuthViewModel
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.ActivityForgotPasswordBinding

class ForgotPassword : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.textlogin.setOnClickListener {
            val intent = Intent(this@ForgotPassword, LoginUser::class.java)
            startActivity(intent)
            finish()
        }

        binding.textRegister.setOnClickListener {
            val intent = Intent(this@ForgotPassword, RegisterUser::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSubmit.setOnClickListener {
            checkValidation()
        }

    }

    private fun checkValidation() {
        val forgotMail = binding.etForgotMail.editText?.text.toString()

        if (forgotMail.isEmpty()) {
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show()
        } else {
            sendMail(forgotMail)
        }

    }

    private fun sendMail(forgotMail: String) {
        authViewModel.sendMail(forgotMail).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Email Sent", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ForgotPassword, LoginUser::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }


}