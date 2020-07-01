package by.afanasyeu.weatherapp.fragment.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.afanasyeu.weatherapp.R
import by.afanasyeu.weatherapp.WeatherApp
import by.afanasyeu.weatherapp.network.HttpResult
import by.afanasyeu.weatherapp.viewModel.CitiesViewModel
import by.afanasyeu.weatherapp.viewModel.CityDetailViewModel
import kotlinx.android.synthetic.main.fragment_city_detail.*
import javax.inject.Inject

class CityDetailFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CityDetailViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        WeatherApp.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.info.observe(viewLifecycleOwner, Observer {
            when (it) {
                is HttpResult.Success ->
                    textViewFeelsValue.text =
                        getString(R.string.degrees, it.data.main.feels_like.toString())
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
        val cityId = requireArguments().getLong(KEY_CITY_ID)
        viewModel.updateInfo(cityId)
    }

    companion object {
        private const val TAG = "CityDetailFragment"
        private const val KEY_CITY_ID = "KEY_CITY_ID"

        fun newInstance(cityId: Long): CityDetailFragment {
            return CityDetailFragment().apply {
                arguments = bundleOf(KEY_CITY_ID to cityId)
            }
        }
    }
}