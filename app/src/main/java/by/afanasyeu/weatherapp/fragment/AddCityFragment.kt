package by.afanasyeu.weatherapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.afanasyeu.weatherapp.R
import by.afanasyeu.weatherapp.WeatherApp
import by.afanasyeu.weatherapp.adapter.AdditionAdapter
import by.afanasyeu.weatherapp.model.City
import by.afanasyeu.weatherapp.network.HttpResult
import by.afanasyeu.weatherapp.viewModel.AddCityViewModel
import kotlinx.android.synthetic.main.fragment_add_city.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.sample
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class AddCityFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AddCityViewModel by viewModels { viewModelFactory }
    private val adapter = AdditionAdapter {
        viewModel.save(City(it.id, it.name, it.main.temp, it.weather[0].icon))
        findNavController().navigateUp()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        WeatherApp.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_city, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        initViewModel()
        initListeners()
    }
    private fun initView() {
        recyclerViewCandidates.adapter = adapter
    }

    private fun initListeners() {
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        lifecycleScope.launchWhenResumed {
            callbackFlow {
                editTextCity.addTextChangedListener {
                    textInputLayoutAddCity.error = null
                    it?.toString()?.let { searchString ->
                        if (searchString.length > 2) offer(searchString)
                    }
                }
                awaitClose()
            }
                .sample(600L)
                .collectLatest {
                    viewModel.search(it)
                }
        }
    }

    private fun initViewModel() {
        viewModel.searchResult.observe(viewLifecycleOwner, Observer {
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
                is HttpResult.Success -> {
                    textInputLayoutAddCity.error = if (it.data.isNotEmpty()) {
                        adapter.submitList(it.data)
                        null
                    } else {
                        getString(R.string.error_no_match)
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        val view = requireActivity().currentFocus
        view?.let { v ->
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}