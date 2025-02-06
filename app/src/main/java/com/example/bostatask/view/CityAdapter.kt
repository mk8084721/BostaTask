package com.example.bostatask.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bostatask.R
import com.example.bostatask.model.City

class CityAdapter(private var cities: List<City>) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.bind(city)
    }
    fun updateData(newCities: List<City>) {
        this.cities = newCities
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = cities.size

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityName: TextView = itemView.findViewById(R.id.cityName)
        private val districtRecyclerView: RecyclerView = itemView.findViewById(R.id.districtRecyclerView)
        private val districtAdapter = DistrictAdapter()

        init {
            districtRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            districtRecyclerView.adapter = districtAdapter

            // Handle city item click to expand/collapse
            itemView.setOnClickListener {
                val city = cities[adapterPosition]
                city.isExpanded = !city.isExpanded
                notifyItemChanged(adapterPosition)
            }
        }

        fun bind(city: City) {
            cityName.text = city.cityName

            // Show/hide districts based on expansion state
            if (city.isExpanded) {
                districtRecyclerView.visibility = View.VISIBLE
                districtAdapter.submitList(city.districts)
            } else {
                districtRecyclerView.visibility = View.GONE
            }
        }
    }
}