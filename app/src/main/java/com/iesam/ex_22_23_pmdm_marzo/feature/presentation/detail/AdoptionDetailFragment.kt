package com.iesam.ex_22_23_pmdm_marzo.feature.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alfsuace.examen_pmdm.R
import com.alfsuace.examen_pmdm.databinding.AdoptionDetailFragmentBinding
import com.google.android.material.snackbar.Snackbar
import com.iesam.ex_22_23_pmdm_marzo.feature.data.AdoptionsDataRepository
import com.iesam.ex_22_23_pmdm_marzo.feature.domain.AdoptionDetail
import com.iesam.ex_22_23_pmdm_marzo.feature.domain.GetAdoptionUseCase
import com.iesam.ex_22_23_pmdm_marzo.feature.presentation.list.AdoptionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdoptionDetailFragment:Fragment() {
    private var _binding: AdoptionDetailFragmentBinding? = null
    private val binding get() = _binding!!

    val args: AdoptionDetailFragmentArgs by navArgs()

    private val viewModel by viewModels<AdoptionDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= AdoptionDetailFragmentBinding.inflate(inflater, container, false)
        setUpView()
        return binding.root
    }

    private fun setUpView() {
        binding.apply {
            detailToolbarFragment.detailToolbar.apply {
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadAdoption(args.adoptionId)
        setUpObservers()
    }

    private fun setUpObservers() {
        val observer = Observer<AdoptionDetailViewModel.UiState>{uiState ->
            bindAdoptionDetail(uiState.adoption!!)
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun bindAdoptionDetail(bind: AdoptionDetail) {
        binding.apply {
            detailToolbarFragment.detailToolbar.title = bind.namePet
            sexText.text = bind.sex
            dateBornText.text = bind.dateBorn
            breedText.text = bind.bread
            sizeText.text = bind.size
            descripionText.text = bind.description
        }
    }

    private fun showError(view: View) {
        Snackbar.make(view, getString(R.string.error), Snackbar.LENGTH_SHORT).show()
    }
}