package com.example.complanschool.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.complanschool.authentication.InputProfileActivity
import com.example.complanschool.databinding.FragmentHomeBinding
import com.example.complanschool.laporan.ListLaporanFasilitas
import com.example.complanschool.laporan.ListLaporanPerson

class HomeFragment : Fragment() {

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root


    return root
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToLaporanPerson.setOnClickListener{
            startActivity(Intent(requireActivity(), InputProfileActivity::class.java))
        }

        binding.btnToLaporanFacility.setOnClickListener{
            startActivity(Intent(requireActivity(), ListLaporanFasilitas::class.java))
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}