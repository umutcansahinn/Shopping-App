package com.example.shoppingapp.feature.basket.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.core.common.loadImage
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.databinding.ItemBasketAdapterBinding

class BasketAdapter(
    private val onDeleteClickListener: (BasketEntity)-> Unit,
    private val onPlusOrMinusClickListener: (BasketEntity,isPlus: Boolean)-> Unit
) : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemBasketAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<BasketEntity>() {
        override fun areItemsTheSame(oldItem: BasketEntity, newItem: BasketEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BasketEntity, newItem: BasketEntity): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, differCallback)

    var basketList: List<BasketEntity>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBasketAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return basketList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            imageViewEntityImage.loadImage(basketList[position].image)
            textViewEntityCategory.text = basketList[position].category
            textViewEntityPrice.text = "${basketList[position].price}$"
            textViewEntityCount.text = basketList[position].itemCount.toString()

            imageViewDelete.setOnClickListener {
                onDeleteClickListener(basketList[position])
            }
            imageViewMinus.setOnClickListener {
                onPlusOrMinusClickListener(basketList[position], false)
            }
            imageViewPlus.setOnClickListener {
                onPlusOrMinusClickListener(basketList[position],true)
            }
        }
    }
}