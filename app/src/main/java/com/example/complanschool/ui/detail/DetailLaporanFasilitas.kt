package com.example.complanschool.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.complanschool.databinding.ActivityDetailLaporanFasilitasBinding

class DetailLaporanFasilitas : AppCompatActivity() {

    private lateinit var binding : ActivityDetailLaporanFasilitasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLaporanFasilitasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.facilityName.text = intent.getStringExtra("nama_fasilitas");
        Glide.with(binding.root)
            .load(intent.getStringExtra("foto_fasilitas"))
            .into(binding.facilityImage)
        binding.laporanDecription.text = intent.getStringExtra("dekripsi")
        binding.laporanTimestamp.text = intent.getStringExtra("timestamp")
    }


}