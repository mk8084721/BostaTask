package com.example.bostatask.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bostatask.R
import com.example.bostatask.model.District

class DistrictAdapter : RecyclerView.Adapter<DistrictAdapter.DistrictViewHolder>() {

    private val districts = mutableListOf<District>()

    fun submitList(newDistricts: List<District>) {
        districts.clear()
        districts.addAll(newDistricts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_district, parent, false)
        return DistrictViewHolder(view)
    }

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) {
        val district = districts[position]
        holder.bind(district)
    }

    override fun getItemCount(): Int = districts.size

    class DistrictViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val districtName: TextView = itemView.findViewById(R.id.districtName)

        fun bind(district: District) {
            districtName.text = district.districtName
        }
    }
}