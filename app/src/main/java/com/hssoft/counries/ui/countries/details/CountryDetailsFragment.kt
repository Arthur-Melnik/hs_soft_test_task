package com.hssoft.counries.ui.countries.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hssoft.counries.databinding.FragmentCountryDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailsFragment : Fragment() {

    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel by activityViewModels<CountryDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
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
        observeUiState()
    }

    private fun observeUiState() {
        viewModel.state.observe(viewLifecycleOwner, ::handleUiState)
    }

    private fun handleUiState(state: CountriesUiState) {
        when (state) {
            is CountrySelected -> binding.country = state.country
        }
    }

}