package com.hssoft.counries.ui.countries.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.hssoft.counries.data.model.Country
import com.hssoft.counries.databinding.FragmentCountriesBinding
import com.hssoft.counries.ui.countries.details.CountryDetailsViewModel
import com.hssoft.counries.ui.countries.list.items.CountryItem
import com.xwray.groupie.GroupieAdapter
import timber.log.Timber

class CountriesFragment : Fragment() {

    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel by viewModels<CountriesViewModel>()
    private val countryDetailsViewModel by activityViewModels<CountryDetailsViewModel>()

    private val countriesAdapter by lazy {
        return@lazy GroupieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        setupRefresh()
        setupRecycler()
        observeUiState()

        viewModel.loadCountries()
    }

    private fun setupRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadCountries(true)
        }
    }

    private fun setupRecycler() {
        binding.recyclerCountries.adapter = countriesAdapter
    }

    private fun observeUiState() {
        viewModel.state.observe(viewLifecycleOwner, ::handleUiState)
    }

    private fun handleUiState(state: CountriesUiState) {
        when (state) {
            is CountriesLoadError -> onError(state.message)
            is CountriesLoaded -> onCountriesLoaded(state.data)
            is CountriesLoading -> binding.swipeRefresh.isRefreshing = state.isLoading
        }
    }

    private fun onCountriesLoaded(countries: List<Country>) {
        countriesAdapter.replaceAll(
            countries.map { country ->
                CountryItem(country, ::onCountryClick)
            }
        )
    }

    private fun onCountryClick(country: Country) {
        countryDetailsViewModel.selectCountry(country)
    }

    private fun onError(message: String?) {
        Timber.e(message)
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}