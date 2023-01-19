package com.bgmi.sports.tournament.userApp.bgmitournament.ViewModel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.bgmi.sports.tournament.userApp.bgmitournament.Auth.LoginUser
import com.bgmi.sports.tournament.userApp.bgmitournament.MainActivity
import com.bgmi.sports.tournament.userApp.bgmitournament.repository.authRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val repository: authRepository

    init {
        repository = authRepository().getInstance()
    }

    fun SignIn(email: String, password: String, context: Context): Task<AuthResult> {
        val task = repository.SignIn(email, password, context)

        return task
    }


    fun SignUp(email: String, password: String, context: Context): Task<AuthResult> {
        val task = repository.SignUp(email, password, context)
        return task
    }

    fun SignOut() {
        repository.SignOut()
    }

    fun sendMail(email: String): Task<Void> {
        val task = repository.sendMail(email)
        return task
    }

    fun currentUser() = repository.currentUser()
}