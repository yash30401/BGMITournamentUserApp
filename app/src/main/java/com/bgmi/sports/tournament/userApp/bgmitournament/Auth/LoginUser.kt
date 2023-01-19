package com.bgmi.sports.tournament.userApp.bgmitournament.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bgmi.sports.tournament.userApp.bgmitournament.MainActivity
import com.bgmi.sports.tournament.userApp.bgmitournament.ViewModel.AuthViewModel
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.ActivityLoginUserBinding

class LoginUser : AppCompatActivity() {

    private lateinit var binding: ActivityLoginUserBinding

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.textRegister.setOnClickListener {
            val intent = Intent(this@LoginUser, RegisterUser::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            checkValidation()
        }

        binding.ForgetPass.setOnClickListener {
            //When User ForgotPass

            val intent = Intent(this@LoginUser, ForgotPassword::class.java)
            startActivity(intent)
            finish()
        }

    }


    private fun checkValidation() {
        val loginMail = binding.etLoginMail.editText?.text.toString()
        val loginPass = binding.etLoginPass.editText?.text.toString()

        if (loginMail.isEmpty() || loginPass.isEmpty()) {
            Toast.makeText(this, "Empty Field!", Toast.LENGTH_SHORT)
                .show() //Showing Toast On Empty Fields
        } else {
            loginUser(loginMail, loginPass) //Login User When Condition does not satisfy
        }
    }

    private fun loginUser(loginMail: String, loginPass: String) {
        authViewModel.SignIn(loginMail, loginPass, this).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this@LoginUser, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (authViewModel.currentUser() != null) {
            val intent = Intent(this@LoginUser, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}