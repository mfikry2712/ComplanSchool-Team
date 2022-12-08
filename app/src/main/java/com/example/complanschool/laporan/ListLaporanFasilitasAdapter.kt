package com.example.complanschool.laporan

import android.app.Activity
import android.content.Intent
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.complan.dataclass.DataLaporanFasilitas
import com.example.complan.dataclass.DataLaporanPerson
import com.example.complanschool.R
import com.example.complanschool.databinding.ItemHistoryBinding
import com.example.complanschool.ui.detail.DetailLaporanFasilitas
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
            Glide.with(itemView)
                .load(item.photo)
                .into(binding.tvFacilityImage)
            binding.tvNamaSuspect.text = item.facilityName
            if (item.timestamp != null) {
                binding.tvTanggal.text = DateUtils.getRelativeTimeSpanString(item.timestamp)
            }
            binding.tvJenis2.text = item.description

            itemView.setOnClickListener {
                val timestamp = item.timestamp?.let { it1 -> DateUtils.getRelativeTimeSpanString(it1) }
                val intent = Intent(itemView.context, DetailLaporanFasilitas::class.java)
                intent.putExtra("nama_fasilitas", item.facilityName)
                intent.putExtra("foto_fasilitas", item.photo)
                intent.putExtra("dekripsi", item.description)
                intent.putExtra("timestamp", timestamp)

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        androidx.core.util.Pair(binding.tvFacilityImage, "fasilitas_image"),
                        androidx.core.util.Pair(binding.tvNamaSuspect, "fasilitas_name"),
                        androidx.core.util.Pair(binding.tvJenis2, "laporan_description"),
                        androidx.core.util.Pair(binding.tvTanggal, "laporan_timestamp"),
                    )
                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }

        }
    }
}
