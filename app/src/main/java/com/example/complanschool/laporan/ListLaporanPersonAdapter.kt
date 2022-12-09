package com.example.complanschool.laporan

import android.app.Activity
import android.content.Intent
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.complan.dataclass.DataLaporanPerson
import com.example.complanschool.R
import com.example.complanschool.databinding.ItemHistoryBinding
import com.example.complanschool.ui.detail.DetailLaporanFasilitas
import com.example.complanschool.ui.detail.DetailLaporanPerson
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

            itemView.setOnClickListener {
                val timestamp = item.timestamp?.let { it1 -> DateUtils.getRelativeTimeSpanString(it1) }
                val intent = Intent(itemView.context, DetailLaporanPerson::class.java)
                intent.putExtra("nama_person", item.suspect)
                intent.putExtra("foto_person", item.photo)
                intent.putExtra("kategori", item.violationType)
                intent.putExtra("dekripsi", item.description)
                intent.putExtra("timestamp", timestamp)

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        androidx.core.util.Pair(binding.tvImage, "person_image"),
                        androidx.core.util.Pair(binding.tvNamaSuspect, "person_name"),
                        androidx.core.util.Pair(binding.tvJenis2, "laporan_description"),
                        androidx.core.util.Pair(binding.tvTanggal, "laporan_timestamp"),
                    )
                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }
        }
    }
}