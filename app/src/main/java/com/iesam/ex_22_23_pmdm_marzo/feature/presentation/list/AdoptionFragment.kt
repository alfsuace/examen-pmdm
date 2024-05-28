package com.iesam.ex_22_23_pmdm_marzo.feature.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfsuace.examen_pmdm.R
import com.alfsuace.examen_pmdm.databinding.AdoptionFragmentBinding
import com.google.android.material.snackbar.Snackbar
import com.iesam.ex_22_23_pmdm_marzo.feature.data.AdoptionsDataRepository
import com.iesam.ex_22_23_pmdm_marzo.feature.domain.GetAdoptionsUseCase
import com.iesam.ex_22_23_pmdm_marzo.feature.presentation.list.adapter.AdoptionItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdoptionFragment : Fragment() {

    private var _binding: AdoptionFragmentBinding? = null
    private val binding get() = _binding!!
    private val adoptionItemAdapter = AdoptionItemAdapter()

    private val viewModel by viewModels<AdoptionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AdoptionFragmentBinding.inflate(inflater, container, false)
        setUpView()
        return binding.root
    }

    private fun setUpView() {
        binding.apply {
            menuToolbar.buttonFavorite.setOnClickListener {
                Snackbar.make(binding.root, "favoritos selecionados", Snackbar.LENGTH_SHORT).show()
            }
            listAdoptionsFeed.apply {
                menuToolbar.toolbar.title = getString(R.string.tool_bar_title)
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adoptionItemAdapter.setOnClick {
                    navigateToDetail(it.id)
                }
                adapter = adoptionItemAdapter
            }
        }
    }

    fun navigateToDetail(id: Int) {
        findNavController().navigate(
            AdoptionFragmentDirections.actionListToDetail(
                adoptionId = id
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        viewModel.getAdoptions()
    }

    private fun setUpObservers() {
        val observer = Observer<AdoptionViewModel.UiState> { uiState ->
            if (uiState.error == null) {
                adoptionItemAdapter.submitList(uiState.adoptions)
            } else {
                showError()
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun showError() {
        Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}