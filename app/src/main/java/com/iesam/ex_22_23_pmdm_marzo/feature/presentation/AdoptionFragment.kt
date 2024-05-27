package com.iesam.ex_22_23_pmdm_marzo.feature.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfsuace.examen_pmdm.R
import com.alfsuace.examen_pmdm.databinding.AdoptionFragmentBinding
import com.iesam.ex_22_23_pmdm_marzo.feature.data.AdoptionsDataRepository
import com.iesam.ex_22_23_pmdm_marzo.feature.domain.GetAdoptionsUseCase
import com.iesam.ex_22_23_pmdm_marzo.feature.presentation.adapter.AdoptionItemAdapter

class AdoptionFragment : Fragment() {

  private var _bindind: AdoptionFragmentBinding? = null
    private val binding get() = _bindind!!
    private val adoptionItemAdapter = AdoptionItemAdapter()

    private val viewModel: AdoptionViewModel by lazy {
        AdoptionViewModel(GetAdoptionsUseCase(AdoptionsDataRepository()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _bindind=AdoptionFragmentBinding.inflate(inflater, container, false)
        setUpView()
        return binding.root
    }

    private fun setUpView() {
        binding.apply {
            listAdoptionsFeed.apply {
                menuToolbar.toolbar.title = getString(R.string.tool_bar_title)
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = adoptionItemAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        viewModel.getAdoptions()
    }

    private fun setUpObservers() {
        val observer = Observer<AdoptionViewModel.UiState>{uiState ->
            if (uiState.error!= null){
                adoptionItemAdapter.submitList(uiState.adoptions)
            }else{
                showError()
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun showError() {
        TODO("Not yet implemented")
    }
}