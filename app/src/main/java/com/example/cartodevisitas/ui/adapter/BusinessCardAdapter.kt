package com.example.cartodevisitas.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cartodevisitas.data.BusinessCard
import com.example.cartodevisitas.databinding.VisitCardItemBinding

class BusinessCardAdapter : ListAdapter<BusinessCard, BusinessCardAdapter.CardViewHolder>(CardComparator()) {

    var listenerShare: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VisitCardItemBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindViews(getItem(position))
    }

    inner class CardViewHolder(val binding: VisitCardItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindViews(card: BusinessCard) {
            binding.usernameTxt.text = card.name
            binding.phoneTxt.text = card.phone
            binding.emailTxt.text = card.email
            binding.companyNameTxt.text = card.company_name
            binding.cardview.setCardBackgroundColor(Color.parseColor(card.cardBackground))
            binding.cardview.setOnClickListener {
                listenerShare(it)
            }
        }
    }
}

class CardComparator : DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem.id == newItem.id
    }

}