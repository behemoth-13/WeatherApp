package by.afanasyeu.weatherapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.afanasyeu.weatherapp.fragment.details.CityDetailFragment
import by.afanasyeu.weatherapp.fragment.details.ThreeDaysDetailFragment
import by.afanasyeu.weatherapp.fragment.details.WeekDetailFragment

class DetailsAdapter(fragment: Fragment, private val cityId: Long) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CityDetailFragment.newInstance(cityId)
            1 -> ThreeDaysDetailFragment()
            else -> WeekDetailFragment()
        }
    }
}