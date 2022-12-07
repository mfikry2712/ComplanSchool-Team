package com.example.complanschool.authentication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.complanschool.Menu
import com.example.complanschool.R
import com.example.complanschool.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var authReference: DatabaseReference

    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var btnGoogle : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var testMail = false
        var testPass = false

        edtEmail = findViewById(R.id.edtMail)
        edtPassword = findViewById(R.id.edtPassword)
        btnGoogle = findViewById(R.id.btnGoogle)

        edtEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                testMail = android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches()
                binding.btnLogin.isEnabled = testPass && testMail
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        edtPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                testPass = s.length >= 6
                binding.btnLogin.isEnabled = testPass && testMail
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Initialize Firebase Auth
        auth = Firebase.auth
        authReference = FirebaseDatabase.getInstance().getReference("user")

        btnGoogle.setOnClickListener {
            signIn()
        }

        binding.btnSignUp.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener{
            val textMail = binding.edtMail.text.toString()
            val textPassword = binding.edtPassword.text.toString()
            signInWithEmailPassword(textMail, textPassword)
        }

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun signInWithEmailPassword(email : String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                val user = auth.currentUser
                updateUI(user)
            }
            else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                Log.w(TAG, "signInWithCredential:failure", it.exception)
                updateUI(null)
            }
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            authReference.child(currentUser.uid).get().addOnSuccessListener{
                if (it.exists()){
                    val o = Intent(this@LoginActivity,  Menu::class.java)
                    startActivity(o)
                    finish()
                }else{
                    val o = Intent(this@LoginActivity,  SelectCode::class.java)
                    startActivity(o)
                    finish()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}