package com.iesam.ex_22_23_pmdm_marzo.feature.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alfsuace.examen_pmdm.databinding.ViewAdoptionItemBinding
import com.iesam.ex_22_23_pmdm_marzo.feature.domain.Adoption

class AdoptionItemViewHolder(itemView: View) : ViewHolder(itemView) {
    lateinit var binding: ViewAdoptionItemBinding

    fun bind(adoption: Adoption){
        binding = ViewAdoptionItemBinding.bind(itemView)

        binding.apply {
            itemTitle.text = adoption.namePet
            itemDesc.text = adoption.shortDescription
            itemGenre.text = adoption.sex
            itemDateBorn.text = adoption.dateBorn
        }
    }
}