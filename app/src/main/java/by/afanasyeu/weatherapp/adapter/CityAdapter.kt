package by.afanasyeu.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import by.afanasyeu.weatherapp.R
import by.afanasyeu.weatherapp.model.City

class CityAdapter(private val onCityClickListener: (City) -> Unit) :
    PagedListAdapter<City, CityViewHolder>(
        object : DiffUtil.ItemCallback<City>() {
            override fun areItemsTheSame(oldItem: City, newItem: City) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: City, newItem: City) = oldItem == newItem
        }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onCityClickListener) }
    }
}