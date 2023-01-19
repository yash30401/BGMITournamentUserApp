package com.bgmi.sports.tournament.userApp.bgmitournament.repository

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.bgmi.sports.tournament.userApp.bgmitournament.Auth.LoginUser
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class authRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()

    @Volatile private var INSTANCE:authRepository?=null

    fun getInstance():authRepository{
        return INSTANCE?: synchronized(this){
            val instance=authRepository()
            INSTANCE=instance
            instance
        }
    }

    fun SignIn(email: String, password: String, context: Context):Task<AuthResult> {
        val task=firebaseAuth.signInWithEmailAndPassword(email, password)

        return task
    }


    fun SignUp(email: String, password: String, context: Context):Task<AuthResult> {
        val task=firebaseAuth.createUserWithEmailAndPassword(email, password)
        return task
    }

    fun SignOut() {
        firebaseAuth.signOut()
    }
    fun sendMail(email: String): Task<Void> {
        val task    =   firebaseAuth.sendPasswordResetEmail(email)
        return task
    }

    fun currentUser() = firebaseAuth.currentUser
}

