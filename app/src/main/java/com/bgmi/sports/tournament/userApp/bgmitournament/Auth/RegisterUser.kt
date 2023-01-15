package com.bgmi.sports.tournament.userApp.bgmitournament.Auth

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import android.util.Log
import androidx.core.graphics.drawable.toDrawable
import com.bgmi.sports.tournament.userApp.bgmitournament.R

import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.ActivityRegisterUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException

class RegisterUser : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterUserBinding
    private lateinit var bitmap: Bitmap
    private lateinit var auth:FirebaseAuth
    private lateinit var storageReference: StorageReference

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