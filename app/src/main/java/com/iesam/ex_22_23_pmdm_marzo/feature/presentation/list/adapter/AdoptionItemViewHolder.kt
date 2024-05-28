package com.iesam.ex_22_23_pmdm_marzo.feature.presentation.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alfsuace.examen_pmdm.R
import com.alfsuace.examen_pmdm.databinding.ViewAdoptionItemBinding
import com.google.android.material.snackbar.Snackbar
import com.iesam.ex_22_23_pmdm_marzo.feature.domain.Adoption
import com.iesam.ex_22_23_pmdm_marzo.feature.domain.AdoptionDetail

class AdoptionItemViewHolder(itemView: View) : ViewHolder(itemView) {
    lateinit var binding: ViewAdoptionItemBinding

    fun bind(adoption: Adoption, onClick: (Adoption) -> Unit){
        binding = ViewAdoptionItemBinding.bind(itemView)
        var isFavorite: Boolean = false

        binding.apply {

            itemTitle.text = adoption.namePet
            itemDesc.text = adoption.shortDescription
            itemGenre.text = adoption.sex
            if (adoption.sex=="Hembra"){
                iconGenre.setImageResource(R.drawable.ic_female)
            }else{
                iconGenre.setImageResource(R.drawable.ic_male)
            }
            itemDateBorn.text = adoption.dateBorn


            buttonFavorite.setOnClickListener {
                isFavorite = !isFavorite
                if (isFavorite) buttonFavorite.setImageResource(R.drawable.ic_liked) else buttonFavorite.setImageResource(R.drawable.ic_like)
                val message = if (isFavorite) "Favorito seleccionado" else "Favorito deseleccionado"
                Snackbar.make(itemView, message, Snackbar.LENGTH_SHORT).show()
            }
            itemView.setOnClickListener {
                onClick(adoption)
            }
        }
    }

}