package com.example.complanschool.ui.status

import android.graphics.BitmapFactory
import android.graphics.Color
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.complanschool.dataclass.DataLaporanPerson
import com.example.complanschool.R
import com.example.complanschool.databinding.ItemHistoryStatusBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class AdapterStatusPerson(
    options: FirebaseRecyclerOptions<DataLaporanPerson>
) : FirebaseRecyclerAdapter<DataLaporanPerson, AdapterStatusPerson.MessageViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_history_status, parent, false)
        val binding = ItemHistoryStatusBinding.bind(view)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MessageViewHolder,
        position: Int,
        model: DataLaporanPerson
    ) {
        holder.bind(model)
    }

    inner class MessageViewHolder(private val binding: ItemHistoryStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataLaporanPerson) {
            val photoStorage = FirebaseStorage.getInstance().reference.child("images/${item.photo.toString()}")
            val localFile = File.createTempFile("tempImage","jpg")
            photoStorage.getFile(localFile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                binding.tvImage.setImageBitmap(bitmap)
            }.addOnFailureListener{
                Toast.makeText(null,"Gagal mengambil data foto", Toast.LENGTH_SHORT)
                    .show()
            }
            binding.tvStatus.text = item.statusType
            setTextColor(item.statusType,binding.tvStatus)
            binding.tvNamaSuspect.text = item.suspect
            if (item.timestamp != null) {
                binding.tvTanggal.text = DateUtils.getRelativeTimeSpanString(item.timestamp)
            }
            binding.tvJenis2.text = item.violationType
        }
    }

    private fun setTextColor(statusType: String?,tvStatus : TextView) {
        if (statusType == "Terkonfirmasi") {
            tvStatus.setTextColor(Color.parseColor("#28DF99"))
        } else {
            tvStatus.setTextColor(Color.parseColor("#FF6464"))
        }
    }
}