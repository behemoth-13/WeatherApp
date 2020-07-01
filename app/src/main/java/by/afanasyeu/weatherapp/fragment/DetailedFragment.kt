package by.afanasyeu.weatherapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.afanasyeu.weatherapp.R
import by.afanasyeu.weatherapp.adapter.DetailsAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_detailed.*
import kotlinx.android.synthetic.main.fragment_detailed.toolbar

class DetailedFragment : Fragment() {

    private lateinit var adapter: DetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initView() {
        val args = DetailedFragmentArgs.fromBundle(requireArguments())
        toolbar.title = args.cityName
        adapter = DetailsAdapter(this, args.cityId)
        pager.adapter = adapter
        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tab_city)
                1 -> getString(R.string.tab_3days)
                else -> getString(R.string.tab_week)
            }
        }.attach()
    }
}