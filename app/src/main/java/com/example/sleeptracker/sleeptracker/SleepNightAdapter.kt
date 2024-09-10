package com.example.sleeptracker.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sleeptracker.R
import com.example.sleeptracker.convertDurationToFormatted
import com.example.sleeptracker.convertNumericQualityToString
import com.example.sleeptracker.database.SleepNight
import com.example.sleeptracker.databinding.ListItemSleepNightBinding

class SleepNightAdapter : RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val itemBinding: ListItemSleepNightBinding ): RecyclerView.ViewHolder(itemBinding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemSleepNightBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources

        holder.itemBinding.run {
            sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilii, res)
            qualityString.text = convertNumericQualityToString(item.sleepQuality, res)
            qualityImage.setImageResource(when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            })
        }
    }
    override fun getItemCount(): Int {
        return data.size
    }


}