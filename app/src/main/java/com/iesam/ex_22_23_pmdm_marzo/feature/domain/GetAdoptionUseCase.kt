package com.iesam.ex_22_23_pmdm_marzo.feature.domain

import com.iesam.ex_22_23_pmdm_marzo.app.Either
import com.iesam.ex_22_23_pmdm_marzo.app.ErrorApp
import com.iesam.ex_22_23_pmdm_marzo.feature.data.AdoptionsDataRepository
import javax.inject.Inject

class GetAdoptionUseCase @Inject constructor(private val repository: AdoptionsDataRepository){

    operator fun invoke(id: Int): Either<ErrorApp, AdoptionDetail> {
        return repository.getDetailAdoption(id)
    }

}
