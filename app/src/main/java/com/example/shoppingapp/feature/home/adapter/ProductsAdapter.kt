package com.example.shoppingapp.feature.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.core.common.loadImage
import com.example.shoppingapp.core.domain.domain_model.DomainModel
import com.example.shoppingapp.databinding.ItemProductsAdapterBinding

class ProductsAdapter(
    private val itemClickListener: ((Int) -> Unit),
    private val list: ArrayList<DomainModel>
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemProductsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductsAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            imageViewProductImage.loadImage(list[position].image)
            textViewProductCategoryName.text = list[position].category
            textViewProductPrice.text = "${list[position].price}$"

            cardView.setOnClickListener {
                itemClickListener.invoke(list[position].id)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<DomainModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}