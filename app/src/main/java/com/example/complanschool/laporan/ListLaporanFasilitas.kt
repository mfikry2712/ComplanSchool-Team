package com.example.complanschool.laporan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complan.dataclass.DataLaporanFasilitas
import com.example.complan.dataclass.DataLaporanPerson
import com.example.complanschool.authentication.LoginActivity
import com.example.complanschool.databinding.ActivityListLaporanFasilitasBinding
import com.example.complanschool.databinding.ActivityListLaporanPersonBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListLaporanFasilitas : AppCompatActivity() {

    private lateinit var binding: ActivityListLaporanFasilitasBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var dbi: DatabaseReference
    private lateinit var kd : String

    private val adapterObs = MutableLiveData<ListLaporanFasilitasAdapter>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListLaporanFasilitasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val msgRef = MutableLiveData<Query>()
        auth = Firebase.auth
        db = Firebase.database
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(this@ListLaporanFasilitas, LoginActivity::class.java))
            finish()
            return
        }

        dbi = FirebaseDatabase.getInstance().getReference("user_sekolah").child(firebaseUser.uid)

        dbi.get().addOnSuccessListener{
            kd =  it.child("schoolCode").value.toString()
            Log.d("test value of kd", kd)
            msgRef.value = db.reference.child("kode_sekolah")
                .child(kd).child("Laporan")
                .child("Laporan Fasilitas")
                .orderByChild("status")
                .equalTo(false)
        }
        msgRef.observe(this){

            if (it != null) {

                Log.d("test kd2 value",it.get().isSuccessful.toString())
                Log.d("test kd2",it.toString())
                val options = FirebaseRecyclerOptions.Builder<DataLaporanFasilitas>()
                    .setQuery(it, DataLaporanFasilitas::class.java)
                    .build()
                Log.d("test options", options.snapshots.toString())


                val manager = LinearLayoutManager(this)
                binding.rcRiwayatLaporanFa.layoutManager = manager
                binding.rcRiwayatLaporanFa.itemAnimator = null
                adapterObs.value = ListLaporanFasilitasAdapter(options)
            }
        }
    }
    override fun onResume() {
        super.onResume()
        adapterObs.observe(this) {
            if (it != null) {
                it.startListening()
                Log.d("adapter", "listening")

                binding.rcRiwayatLaporanFa.adapter = it
            }
        }
    }

    override fun onPause() {
        adapterObs.value?.stopListening()
        super.onPause()
    }
}