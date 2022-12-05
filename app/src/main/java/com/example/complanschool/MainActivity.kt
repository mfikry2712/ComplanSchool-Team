package com.example.complanschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.complanschool.auth.LoginActivity
import com.example.complanschool.databinding.ActivityMainBinding
import com.example.complanschool.laporan.ListLaporanActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding.btnToListLaporan.setOnClickListener {
            //Toast.makeText(this, auth.uid, Toast.LENGTH_LONG).show()
            val goToListLaporan = Intent(this@MainActivity, ListLaporanActivity::class.java)
            startActivity(goToListLaporan)
        }

    }
}