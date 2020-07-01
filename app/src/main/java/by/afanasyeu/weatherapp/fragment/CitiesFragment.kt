package by.afanasyeu.weatherapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import by.afanasyeu.weatherapp.R
import by.afanasyeu.weatherapp.WeatherApp
import by.afanasyeu.weatherapp.adapter.CityAdapter
import by.afanasyeu.weatherapp.network.HttpResult
import by.afanasyeu.weatherapp.viewModel.CitiesViewModel
import kotlinx.android.synthetic.main.fragment_cities.*
import javax.inject.Inject


class CitiesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CitiesViewModel by viewModels { viewModelFactory }
    private val adapter =
        CityAdapter {
            val action = CitiesFragmentDirections.actionCitiesFragmentToDetailedFragment(it.id, it.name)
            findNavController().navigate(action)
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        WeatherApp.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
        viewModel.updateCities()
    }

    private fun initView() {
        recyclerViewCities.adapter = adapter
        recyclerViewCities.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        swipeContainerCities.setOnRefreshListener {
            viewModel.updateCities()
        }
        fabAddCity.setOnClickListener {
            findNavController().navigate(R.id.action_citiesFragment_to_addCityFragment)
        }
    }

    private fun initViewModel() {
        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.updated.observe(viewLifecycleOwner, Observer {
            swipeContainerCities.isRefreshing = false
            when (it) {
                is HttpResult.Error -> Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_default_error),
                    Toast.LENGTH_SHORT
                ).show()
                is HttpResult.NoInternet -> Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_no_internet),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}