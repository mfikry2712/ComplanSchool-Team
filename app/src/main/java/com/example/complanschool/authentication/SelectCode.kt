package com.example.complanschool.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.complanschool.databinding.ActivitySelectCodeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SelectCode : AppCompatActivity() {

    private lateinit var binding: ActivitySelectCodeBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var dbi: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        dbi = Firebase.database
        binding.btnConfirmCode.setOnClickListener {
            val schoolCode = binding.edtCodeConfirm.text.toString()
            dbi.reference.child("kode_sekolah").child(schoolCode).get().addOnSuccessListener {
                if (it.exists()) {
                    val toInputProfile =
                        Intent(this@SelectCode, InputUserProfileActivity::class.java)
                    toInputProfile.putExtra("schoolCode", schoolCode)
                    startActivity(toInputProfile)
                    finish()
                }else{
                    Toast.makeText(this@SelectCode,"Kode sekolah tidak ada",Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnNoCode.setOnClickListener{
            startActivity(Intent(this@SelectCode,InputSchoolProfileActivity::class.java))
            finish()
        }
    }
}