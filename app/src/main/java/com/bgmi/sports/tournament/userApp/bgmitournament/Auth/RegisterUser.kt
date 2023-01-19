package com.bgmi.sports.tournament.userApp.bgmitournament.Auth

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.ActivityRegisterUserBinding
import com.bgmi.sports.tournament.userApp.bgmitournament.ViewModel.AuthViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.io.IOException

class RegisterUser : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterUserBinding
    private lateinit var bitmap: Bitmap
    private lateinit var auth: FirebaseAuth
    private lateinit var storageReference: StorageReference


    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        auth = FirebaseAuth.getInstance()
        storageReference = FirebaseStorage.getInstance().getReference().child("UserImages")

        binding.userProfile.setOnClickListener {
            openGallery()
        }

        binding.textLogin.setOnClickListener {
            val intent = Intent(this, LoginUser::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnRegister.setOnClickListener {
            checkValidation()
        }

    }

    private fun checkValidation() {
        if (binding.etName.editText?.text.toString()
                .isEmpty() || binding.etMail.editText?.text.toString()
                .isEmpty() || binding.etPass.editText?.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "Empty Field!", Toast.LENGTH_SHORT).show()
        } else {
            registerUser()
        }
    }

    private fun registerUser() {
        val userMail = binding.etMail.editText?.text.toString()
        val userPass = binding.etPass.editText?.text.toString()

        authViewModel.SignUp(userMail, userPass, applicationContext)
        uploadImage()

    }

    private fun uploadImage() {
        val currentUser = authViewModel.currentUser()

        val baos = ByteArrayOutputStream()


        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        } catch (e: Exception) {
            Toast.makeText(this, "Please Upload User Profile", Toast.LENGTH_SHORT).show()
        }

        val finalImage = baos.toByteArray()

        val filePath = storageReference.child("${currentUser}.jpg")

        val uploadTask = filePath.putBytes(finalImage)

        uploadTask.addOnCompleteListener(object : OnCompleteListener<UploadTask.TaskSnapshot> {
            override fun onComplete(task: Task<UploadTask.TaskSnapshot>) {
                filePath.downloadUrl.addOnSuccessListener { uri ->
                    updateUser(uri)
                }

            }

        })
    }

    private fun updateUser(uri: Uri) {
        val userProfileChangeRequest = UserProfileChangeRequest.Builder()
            .setDisplayName(binding.etName.editText?.text.toString())
            .setPhotoUri(uri)
            .build()

        auth.currentUser?.updateProfile(userProfileChangeRequest)
        auth.signOut()
        val intent = Intent(this, LoginUser::class.java)
        startActivity(intent)
        finish()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101 && resultCode == RESULT_OK) {
            val uri = data?.data

            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)

            } catch (e: IOException) {
                Log.d("ERROR", e.printStackTrace().toString())
            }

            binding.userProfile.setImageBitmap(bitmap)
        }
    }
}