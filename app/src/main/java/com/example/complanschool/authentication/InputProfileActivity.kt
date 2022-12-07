package com.example.complanschool.authentication

import com.example.complan.dataclass.UserProfile
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.complanschool.Menu
import com.example.complanschool.R
import com.example.complanschool.databinding.ActivityInputProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.streams.asSequence

class InputProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputProfileBinding
    private lateinit var userName : EditText
    private lateinit var userPosition : EditText
    private lateinit var schoolName : EditText
    private lateinit var principal : EditText
    private lateinit var schoolDomicile : EditText
    private lateinit var schoolCode : EditText

    private lateinit var auth: FirebaseAuth
    private lateinit var dbi: FirebaseDatabase


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = findViewById(R.id.edtName)
        userPosition = findViewById(R.id.edtPosition)
        schoolName = findViewById(R.id.edtSchoolName)
        principal = findViewById(R.id.edtPrincipal)
        schoolDomicile = findViewById(R.id.edtDomicile)
        schoolCode = findViewById(R.id.edtCode)

        auth = Firebase.auth
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        dbi = Firebase.database

        binding.btnSend.setOnClickListener{
            val schoolKd = if (schoolCode.text.isEmpty()){
                val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
                java.util.Random().ints(5, 0, source.length)
                    .asSequence()
                    .map(source::get)
                    .joinToString("")
            }else{
                schoolCode.text.toString()
            }

            val dataSchool = UserProfile(
                schoolName = schoolName.text.toString(),
                principal = principal.text.toString(),
                domicile = schoolDomicile.text.toString(),
                schoolCode = schoolKd
            )

            val dataRegistered = UserProfile(
                userName.text.toString(),
                userPosition.text.toString(),
                schoolCode = schoolKd
            )

            dbi.reference.child(CHILD_USER)
                .child(firebaseUser.uid)
                .setValue(dataRegistered){ error, _ ->
                if (error != null) {
                    Toast.makeText(this, "Error" + error.message, Toast.LENGTH_SHORT).show()
                } else {
                    dbi.reference.child(CHILD_SCHOOL)
                        .child(schoolKd)
                        .setValue(dataSchool) { errorStatus, _ ->
                            if (errorStatus != null) {
                                Toast.makeText(this, "Error" + errorStatus.message, Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@InputProfileActivity, Menu::class.java))
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