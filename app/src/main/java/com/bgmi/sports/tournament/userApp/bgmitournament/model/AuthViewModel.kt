package com.bgmi.sports.tournament.userApp.bgmitournament.model

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.bgmi.sports.tournament.userApp.bgmitournament.Auth.LoginUser
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel:ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun SignIn(email:String, password:String,context: Context){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {task->
            if(task.isSuccessful){
                val intent= Intent(context,LoginUser::class.java)
                context.startActivity(intent)
            }else{
                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun SignUp(email: String,password: String,context: Context){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(context, "Registration Successfull", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun SignOut(){
        firebaseAuth.signOut()
    }

    fun currentUser() = firebaseAuth.currentUser
}