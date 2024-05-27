package com.iesam.ex_22_23_pmdm_marzo.feature.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alfsuace.examen_pmdm.R
import com.iesam.ex_22_23_pmdm_marzo.feature.domain.Adoption
import com.iesam.ex_22_23_pmdm_marzo.feature.presentation.AdoptionDiffUtil

class AdoptionItemAdapter: ListAdapter<Adoption, AdoptionItemViewHolder>(AdoptionDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdoptionItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_adoption_item, parent, false)
        return AdoptionItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
    override fun onBindViewHolder(holder: AdoptionItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}