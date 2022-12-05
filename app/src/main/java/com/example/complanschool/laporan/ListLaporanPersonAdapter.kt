package com.example.complanschool.laporan

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.complan.dataclass.DataLaporanPerson
import com.example.complanschool.R
import com.example.complanschool.databinding.ItemHistoryBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class ListLaporanPersonAdapter(
    options: FirebaseRecyclerOptions<DataLaporanPerson>
) : FirebaseRecyclerAdapter<DataLaporanPerson, ListLaporanPersonAdapter.MessageViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_history, parent, false)
        val binding = ItemHistoryBinding.bind(view)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MessageViewHolder,
        position: Int,
        model: DataLaporanPerson
    ) {
        holder.bind(model)
    }

    inner class MessageViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataLaporanPerson) {
            binding.tvNamaSuspect.text = item.suspect
            if (item.timestamp != null) {
                binding.tvTanggal.text = DateUtils.getRelativeTimeSpanString(item.timestamp)
            }
            binding.tvJenis2.text = item.description
        }
    }
}