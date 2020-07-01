package by.afanasyeu.weatherapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.afanasyeu.weatherapp.model.rest.CityDto
import kotlinx.android.synthetic.main.item_add_city.view.*
import kotlinx.android.synthetic.main.item_add_city.view.textViewName

class AdditionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(city: CityDto, onCityClickListener: (CityDto) -> Unit) {
        itemView.apply {
            textViewName.text = city.name
            textViewRegion.text = city.sys.country
            linearLayoutAddItem.setOnClickListener {
                onCityClickListener(city)
            }
        }
    }
}