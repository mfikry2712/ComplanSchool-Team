package com.example.complanschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.complanschool.authentication.LoginActivity
import com.example.complanschool.authentication.SelectCode
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var authReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }else{

            authReference = FirebaseDatabase.getInstance().getReference("user_sekolah")

            Handler(Looper.getMainLooper()).postDelayed({
                authReference.child(firebaseUser.uid).get().addOnSuccessListener{
                    if (it.exists()){
                        startActivity(Intent(this@MainActivity,  Menu::class.java))
                    }else{
                        startActivity(Intent(this@MainActivity,  SelectCode::class.java))
                    }
                }.addOnFailureListener{
                    Log.d("Error Failure",it.message.toString())
                }
                finish()
            }, 2000)

        }}


}
