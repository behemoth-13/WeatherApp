package by.afanasyeu.weatherapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.afanasyeu.weatherapp.R
import by.afanasyeu.weatherapp.model.City
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.item_city.view.*

class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(city: City, onCityClickListener: (City) -> Unit) {
        itemView.apply {
            linearLayoutItem.setOnClickListener { onCityClickListener(city) }
            textViewName.text = city.name
            city.temp?.let {
                textViewTemp.text = context.getString(R.string.degrees, it.toString())
            }
            city.icon?.let {
                Glide.with(this)
                    .load("http://openweathermap.org/img/wn/$it@2x.png")
                    .transform(CenterCrop())
                    .into(imageWeather)
            }
        }
    }
}