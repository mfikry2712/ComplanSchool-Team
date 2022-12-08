package com.example.complanschool.laporan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complan.dataclass.DataLaporanPerson
import com.example.complanschool.authentication.LoginActivity
import com.example.complanschool.databinding.ActivityListLaporanPersonBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListLaporanPerson : AppCompatActivity() {

    private lateinit var binding: ActivityListLaporanPersonBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var dbi: DatabaseReference
    private lateinit var dbo: DatabaseReference
    private lateinit var kd : String

    private val adapterObs = MutableLiveData<ListLaporanPersonAdapter>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListLaporanPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val msgRef = MutableLiveData<DatabaseReference>()
        auth = Firebase.auth
        db = Firebase.database
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(this@ListLaporanPerson, LoginActivity::class.java))
            finish()
            return
        }

        dbi = FirebaseDatabase.getInstance().getReference("user_sekolah").child(firebaseUser.uid)



        dbi.get().addOnSuccessListener{
            kd =  it.child("schoolCode").value.toString()
            Log.d("test value of kd", kd)

            msgRef.value = db.reference.child("kode_sekolah")
                .child(kd) .child("Laporan")
                .child("Laporan Orang")
        }.addOnFailureListener{
            Log.d("kesalahan", it.message.toString())
        }
        msgRef.observe(this){

            if (it != null) {

                Log.d("test kd2 value",it.get().isSuccessful.toString())
                Log.d("test kd2",it.toString())
                val options = FirebaseRecyclerOptions.Builder<DataLaporanPerson>()
                    .setQuery(it, DataLaporanPerson::class.java)
                    .build()
                Log.d("test options", options.snapshots.toString())


                val manager = LinearLayoutManager(this)
                binding.rcRiwayatLaporanOk.layoutManager = manager
                binding.rcRiwayatLaporanOk.itemAnimator = null
                adapterObs.value = ListLaporanPersonAdapter(options)
            }
        }
    }
    override fun onResume() {
        super.onResume()
        adapterObs.observe(this) {
            if (it != null) {
                it.startListening()
                Log.d("adapter", "listening")

                binding.rcRiwayatLaporanOk.adapter = it
            }
        }
    }

    override fun onPause() {
        adapterObs.value?.stopListening()
        super.onPause()
    }
}