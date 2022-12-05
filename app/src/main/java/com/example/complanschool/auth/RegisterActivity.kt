package com.example.complanschool.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.complanschool.R
import com.example.complanschool.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener{
            val textMail = binding.edtMail.text.toString()
            val textPassword = binding.edtPassword.text.toString()
//            val textConfirmPassword = binding.edtConfirmPassword.text.toString()

//            if (!textMail.isEmpty() && !textPassword.isEmpty() && !textConfirmPassword.isEmpty()){
//                if(textConfirmPassword.equals(textPassword)){
//                    signUp(textMail,textPassword)
            auth.createUserWithEmailAndPassword(textMail, textPassword).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                }else{
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
//                }else{
//                    Toast.makeText(this, "Confirm Password ber beda", Toast.LENGTH_LONG).show()
//                }
//            }else{
//                Toast.makeText(this, "Harap isi semua field!", Toast.LENGTH_SHORT).show()
//            }
        }

//        binding.btnLogin.setOnClickListener{
//            val textMail = binding.edtMail.text.toString()
//            val textPassword = binding.edtPassword.text.toString()
//            val textConfirmPassword = binding.edtConfirmPassword.text.toString()

//            if (!textMail.isEmpty() && !textPassword.isEmpty() && !textConfirmPassword.isEmpty()){
//                if(textConfirmPassword.equals(textPassword)){
//            signUp(textMail,textPassword)
//                }else{
//                    Toast.makeText(this, "Confirm Password ber beda", Toast.LENGTH_LONG).show()
//                }
//            }else{
//                Toast.makeText(this, "Harap isi semua field!", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

//    private fun signUp(email : String, password : String){
//        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
//            if (it.isSuccessful){
//                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_LONG).show()
//                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
//            }else{
//                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
//            }
//        }
//    }
}