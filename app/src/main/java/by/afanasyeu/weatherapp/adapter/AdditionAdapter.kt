package by.afanasyeu.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.afanasyeu.weatherapp.R
import by.afanasyeu.weatherapp.model.rest.CityDto

class AdditionAdapter(private val onCityClickListener: (CityDto) -> Unit): ListAdapter<CityDto, AdditionViewHolder>(
    object : DiffUtil.ItemCallback<CityDto>() {
        override fun areItemsTheSame(oldItem: CityDto, newItem: CityDto) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CityDto, newItem: CityDto) = oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_add_city, parent, false)
        return AdditionViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdditionViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onCityClickListener) }
    }
}