package com.example.complanschool.authentication

import com.example.complan.dataclass.UserProfile
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.complanschool.Menu
import com.example.complanschool.R
import com.example.complanschool.databinding.ActivityInputUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.streams.asSequence

class InputUserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputUserProfileBinding
    private lateinit var userName : EditText
    private lateinit var userPosition : EditText

    private lateinit var auth: FirebaseAuth
    private lateinit var dbi: FirebaseDatabase


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = findViewById(R.id.edtName)
        userPosition = findViewById(R.id.edtPosition)

        auth = Firebase.auth
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        dbi = Firebase.database

        val schoolKd = intent.getStringExtra("schoolCode")!!

        binding.btnSend.setOnClickListener{
            val dataRegistered = UserProfile(
                userName.text.toString(),
                userPosition.text.toString()
            )

            dbi.reference.child(CHILD_USER)
                .child(firebaseUser.uid)
                .setValue(dataRegistered){ error, _ ->
                if (error != null) {
                    Toast.makeText(this, "Error" + error.message, Toast.LENGTH_SHORT).show()
                } else {
                    dbi.reference.child(CHILD_SCHOOL).child(schoolKd).child(
                        CHILD_USER
                    )
                        .child(firebaseUser.uid)
                        .setValue(dataRegistered) { errorStatus, _ ->
                            if (errorStatus != null) {
                                Toast.makeText(this, "Error" + errorStatus.message, Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@InputUserProfileActivity, Menu::class.java))
                                finish()
                            }
                        }
                }
            }
        }
    }

    companion object{
        const val CHILD_USER = "user_sekolah"
        const val CHILD_SCHOOL = "kode_sekolah"
    }
}