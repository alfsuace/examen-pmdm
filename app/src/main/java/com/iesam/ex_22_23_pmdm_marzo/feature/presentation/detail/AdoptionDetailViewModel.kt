package com.iesam.ex_22_23_pmdm_marzo.feature.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iesam.ex_22_23_pmdm_marzo.app.ErrorApp
import com.iesam.ex_22_23_pmdm_marzo.feature.domain.AdoptionDetail
import com.iesam.ex_22_23_pmdm_marzo.feature.domain.GetAdoptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdoptionDetailViewModel @Inject constructor(private val getAdoptionUseCase: GetAdoptionUseCase) : ViewModel() {


    private var _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadAdoption(id: Int) {
        _uiState.postValue(UiState(isLoading = true))
        getAdoptionUseCase.invoke(id).fold(
            {responseError(it)},
            {responseSucces(it)}
        )
    }
    private fun responseError(errorApp: ErrorApp) {
        _uiState.postValue(UiState(error = errorApp))
    }

    private fun responseSucces(adoptions: AdoptionDetail) {
        _uiState.postValue(UiState(adoption = adoptions))
    }
    data class UiState(
        val isLoading: Boolean = false,
        val adoption: AdoptionDetail? = null,
        val error: ErrorApp? = null
    )
}