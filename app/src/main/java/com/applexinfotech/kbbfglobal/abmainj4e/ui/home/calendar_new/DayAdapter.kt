package com.applexinfotech.kbbfglobal.abmainj4e.ui.home.calendar_new

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applexinfotech.kbbfglobal.databinding.ItemDayViewBinding

class DayAdapter(
    val context: Context,
    val list: ArrayList<String>,
    val listener: ItemListener
) :
    RecyclerView.Adapter<DayAdapter.DataHolder>() {


    inner class DataHolder(val binding: ItemDayViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind() {
            binding.root.setOnClickListener {
                listener.onItemClick(position)
            }
            when (position) {
                0 -> {
                    binding.tvTime.text = "12 am"
                }
                1 -> {
                    binding.tvTime.text = "1 am"
                }
                2 -> {
                    binding.tvTime.text = "2 am"

                }
                3 -> {
                    binding.tvTime.text = "3 am"

                }
                4 -> {
                    binding.tvTime.text = "4 am"
                }
                5 -> {
                    binding.tvTime.text = "5 am"
                }
                6 -> {
                    binding.tvTime.text = "6 am"
                }
                7 -> {
                    binding.tvTime.text = "7 am"
                }
                8 -> {
                    binding.tvTime.text = "8 am"
                }
                9 -> {
                    binding.tvTime.text = "9 am"
                }
                10 -> {
                    binding.tvTime.text = "10 am"
                }
                11 -> {
                    binding.tvTime.text = "11 am"
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val binding =
            ItemDayViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataHolder(binding)
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 12
    }

    interface ItemListener {
        fun onItemClick(position: Int)

    }
}