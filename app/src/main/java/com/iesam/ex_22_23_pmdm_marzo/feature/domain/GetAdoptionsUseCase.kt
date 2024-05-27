package com.iesam.ex_22_23_pmdm_marzo.feature.domain

import com.iesam.ex_22_23_pmdm_marzo.app.Either
import com.iesam.ex_22_23_pmdm_marzo.app.ErrorApp

class GetAdoptionsUseCase(private val adoptionRepository: AdoptionRepository){
    operator fun invoke(): Either<ErrorApp, List<Adoption>> {
        return adoptionRepository.getAdoptions()
    }
}