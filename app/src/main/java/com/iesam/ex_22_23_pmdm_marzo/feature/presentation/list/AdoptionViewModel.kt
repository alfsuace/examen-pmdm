package com.iesam.ex_22_23_pmdm_marzo.feature.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.ex_22_23_pmdm_marzo.app.ErrorApp
import com.iesam.ex_22_23_pmdm_marzo.feature.domain.Adoption
import com.iesam.ex_22_23_pmdm_marzo.feature.domain.GetAdoptionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdoptionViewModel @Inject constructor(
    private val getAdoptionsUseCase: GetAdoptionsUseCase
) : ViewModel() {
    private var _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState

    fun getAdoptions() {
        _uiState.postValue(UiState(isLoading = true))
        viewModelScope.launch {
            val adoptions = getAdoptionsUseCase.invoke()
            adoptions.fold(
                {responseError(it)},
                {responseSucces(it)}
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) {
        _uiState.postValue(UiState(error = errorApp))
    }

    private fun responseSucces(adoptions: List<Adoption>) {
        _uiState.postValue(UiState(adoptions = adoptions))
    }

    data class UiState(
        val isLoading: Boolean = false,
        val error: ErrorApp? = null,
        val adoptions: List<Adoption> = emptyList()
    )
}