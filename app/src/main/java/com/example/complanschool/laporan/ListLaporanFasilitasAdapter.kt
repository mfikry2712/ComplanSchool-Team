package com.example.complanschool.laporan

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.complan.dataclass.DataLaporanFasilitas
import com.example.complan.dataclass.DataLaporanPerson
import com.example.complanschool.R
import com.example.complanschool.databinding.ItemHistoryBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class ListLaporanFasilitasAdapter(
    options: FirebaseRecyclerOptions<DataLaporanFasilitas>
) : FirebaseRecyclerAdapter<DataLaporanFasilitas, ListLaporanFasilitasAdapter.MessageViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_history, parent, false)
        val binding = ItemHistoryBinding.bind(view)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MessageViewHolder,
        position: Int,
        model: DataLaporanFasilitas
    ) {
        holder.bind(model)
    }

    inner class MessageViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataLaporanFasilitas) {
            binding.tvNamaSuspect.text = item.facilityName
            if (item.timestamp != null) {
                binding.tvTanggal.text = DateUtils.getRelativeTimeSpanString(item.timestamp)
            }
            binding.tvJenis2.text = item.description
        }
    }
}