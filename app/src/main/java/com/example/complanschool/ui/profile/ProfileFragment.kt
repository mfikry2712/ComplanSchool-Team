package com.example.complanschool.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.complanschool.authentication.LoginActivity
import com.example.complanschool.databinding.FragmentNotificationsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

private var _binding: FragmentNotificationsBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference
    private lateinit var dbi: DatabaseReference
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
    val root: View = binding.root
      auth = Firebase.auth
      val firebaseUser = auth.currentUser
      if (firebaseUser == null) {
          // Not signed in, launch the Login activity
          startActivity(Intent(requireActivity(), LoginActivity::class.java))
      }

      db = FirebaseDatabase.getInstance().getReference("user_sekolah").child(firebaseUser!!.uid)
      dbi = FirebaseDatabase.getInstance().getReference("kode_sekolah")

      db.get().addOnSuccessListener{ snapshot ->
          val kdSekolah =  snapshot.child("schoolCode").value.toString()
          val nmUser =  snapshot.child("userName").value.toString()
          val posUser =  snapshot.child("userPosition").value.toString()

          dbi.child(kdSekolah).get().addOnSuccessListener{
              val domicile =  it.child("domicile").value.toString()
              val principal =  it.child("principal").value.toString()
              val schoolName =  it.child("schoolName").value.toString()

              binding.tvName.text = nmUser
              binding.tvSchoolCode.text = kdSekolah
              binding.tvPos.text = posUser
              binding.tvDomicile.text = domicile
              binding.tvPrincipal.text = principal
              binding.tvSchoolName.text = schoolName
          }
      }

      binding.btnSignOut.setOnClickListener{
          auth.signOut()
          startActivity(Intent(requireActivity(), LoginActivity::class.java))
          requireActivity().finish()
      }

    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}