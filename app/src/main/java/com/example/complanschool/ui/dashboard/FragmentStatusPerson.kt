package com.example.complanschool.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complan.dataclass.DataLaporanFasilitas
import com.example.complan.dataclass.DataLaporanPerson
import com.example.complanschool.authentication.LoginActivity
import com.example.complanschool.databinding.FragmentStatusFasilitasBinding
import com.example.complanschool.databinding.FragmentStatusPersonBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FragmentStatusPerson : Fragment() {

    private var _binding: FragmentStatusPersonBinding? = null

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var dbi: DatabaseReference
    private lateinit var kd : String

    private val binding get() = _binding!!

    private val adapterObs = MutableLiveData<AdapterStatusPerson>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatusPersonBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val msgRef = MutableLiveData<Query>()
        auth = Firebase.auth
        db = Firebase.database
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }else
        {
            Log.d(null, "ada"+firebaseUser.uid)
            dbi = FirebaseDatabase.getInstance().getReference("user_sekolah").child(firebaseUser.uid)
        }

        dbi.get().addOnSuccessListener{
            kd =  it.child("schoolCode").value.toString()
            Log.d("test value of kd", kd)
            msgRef.value = db.reference.child("kode_sekolah")
                .child(kd).child("Laporan")
                .child("Laporan Orang")
                .orderByChild("status")
                .equalTo(true)
        }
        msgRef.observe(requireActivity()){

            if (it != null) {

                Log.d("test kd2 value",it.get().isSuccessful.toString())
                Log.d("test kd2",it.toString())
                val options = FirebaseRecyclerOptions.Builder<DataLaporanPerson>()
                    .setQuery(it, DataLaporanPerson::class.java)
                    .build()
                Log.d("test options", options.snapshots.toString())


                val manager = LinearLayoutManager(requireActivity().applicationContext)
                binding.rcRiwayatLaporan.layoutManager = manager
                binding.rcRiwayatLaporan.itemAnimator = null
                adapterObs.value = AdapterStatusPerson(options)
            }
        }


    }

    override fun onResume() {
        super.onResume()
        adapterObs.observe(requireActivity()) {
            if (it != null) {
                it.startListening()
                Log.d("adapter", "listening")

                binding.rcRiwayatLaporan.adapter = it
            }
        }
    }

    override fun onPause() {
        //adapter.stopListening()
        adapterObs.value?.stopListening()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}