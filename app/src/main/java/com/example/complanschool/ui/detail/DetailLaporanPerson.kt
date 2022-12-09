package com.example.complanschool.ui.detail

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.complanschool.authentication.LoginActivity
import com.example.complanschool.databinding.ActivityDetailLaporanPersonBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class DetailLaporanPerson : AppCompatActivity() {

    private lateinit var binding : ActivityDetailLaporanPersonBinding
    private lateinit var db: DatabaseReference
    private lateinit var dbi: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLaporanPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseDatabase.getInstance().getReference("kode_sekolah")

        auth = Firebase.auth

        val photoName = intent.getStringExtra("foto_person").toString()
        val photoStorage = FirebaseStorage.getInstance().reference.child("images/$photoName")

        val localFile = File.createTempFile("tempImage", "jpg")
        photoStorage.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            binding.personImage.setImageBitmap(bitmap)
        }.addOnFailureListener {
            Toast.makeText(
                this@DetailLaporanPerson,
                "Gagal mengambil data foto",
                Toast.LENGTH_SHORT
            )
                .show()
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.category.text = intent.getStringExtra("kategori")
        binding.personName.text = intent.getStringExtra("nama_person")
        binding.laporanDecription.text = intent.getStringExtra("dekripsi")
        binding.laporanTimestamp.text = intent.getStringExtra("timestamp")


        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
        dbi = FirebaseDatabase.getInstance().getReference("user_sekolah").child(firebaseUser.uid)
        binding.btnRead.setOnClickListener {
            updateLaporan("Terkonfirmasi",photoName)
        }

        binding.btnDelete.setOnClickListener {
            updateLaporan("Tidak Relevan",photoName)
        }

    }
    private fun updateLaporan(status : String, photo :String){

        dbi.get().addOnSuccessListener { snapshot ->
            val kdSekolah = snapshot.child("schoolCode").value
            db.child(kdSekolah.toString()).child("Laporan").child("Laporan Orang").orderByChild("photo").equalTo(photo)
                .addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach{
                            val key : String = it.key.toString()
                            Log.d("Tag",key)
                            val gas = mapOf(
                                "status" to true,
                                "statusType" to status
                            )
                            db.child(kdSekolah.toString()).child("Laporan").child("Laporan Orang").child(key).updateChildren(gas)
                                .addOnSuccessListener {
                                    Toast.makeText(this@DetailLaporanPerson,"Berhasil",Toast.LENGTH_SHORT).show()
                                }.addOnFailureListener{
                                    Toast.makeText(this@DetailLaporanPerson,"Error",Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
        }
}


}