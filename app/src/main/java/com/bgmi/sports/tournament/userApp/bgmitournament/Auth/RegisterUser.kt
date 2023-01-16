package com.bgmi.sports.tournament.userApp.bgmitournament.Auth

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import android.util.Log
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import com.bgmi.sports.tournament.userApp.bgmitournament.R

import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.ActivityRegisterUserBinding
import com.bgmi.sports.tournament.userApp.bgmitournament.model.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException

class RegisterUser : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterUserBinding
    private lateinit var bitmap: Bitmap
    private lateinit var auth:FirebaseAuth
    private lateinit var storageReference: StorageReference


    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        auth=FirebaseAuth.getInstance()
        storageReference=FirebaseStorage.getInstance().getReference().child("UserImages")

        binding.userProfile.setOnClickListener {
            openGallery()
        }

        binding.textLogin.setOnClickListener {
            val intent=Intent(this,LoginUser::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            checkValidation()
        }

    }

    private fun checkValidation() {
        if(binding.etName.editText?.text.toString().isEmpty() || binding.etMail.editText?.text.toString().isEmpty() || binding.etPass.editText?.text.toString().isEmpty()){
            Toast.makeText(this, "Empty Field!", Toast.LENGTH_SHORT).show()
        }else{
            registerUser()
        }
    }

    private fun registerUser() {

    }

    private fun openGallery() {
        val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==101&&resultCode== RESULT_OK){
            val uri=data?.data

            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)

            }catch (e:IOException){
                Log.d("ERROR",e.printStackTrace().toString())
            }

            binding.userProfile.setImageBitmap(bitmap)
        }
    }
}