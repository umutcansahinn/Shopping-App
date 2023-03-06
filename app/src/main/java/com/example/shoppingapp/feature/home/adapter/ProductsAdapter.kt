package com.example.shoppingapp.feature.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.core.common.loadImage
import com.example.shoppingapp.core.domain.modelUi.ProductUiModel
import com.example.shoppingapp.databinding.ItemProductsAdapterBinding

class ProductsAdapter(private val itemClickListener:((Int)-> Unit)) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemProductsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<ProductUiModel>() {
        override fun areItemsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, differCallback)

    var productsList: List<ProductUiModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductsAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            imageViewProductImage.loadImage(productsList[position].image)
            textViewProductCategoryName.text = productsList[position].category
            textViewProductPrice.text = "${productsList[position].price}$"

            cardView.setOnClickListener {
                itemClickListener.invoke(productsList[position].id)
            }
        }
    }
}