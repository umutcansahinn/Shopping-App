package com.example.shoppingapp.feature.basket

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.core.common.gone
import com.example.shoppingapp.core.common.viewBinding
import com.example.shoppingapp.core.common.visible
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.databinding.FragmentBasketBinding
import com.example.shoppingapp.feature.basket.adapter.BasketAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.util.EventListener

@AndroidEntryPoint
class BasketFragment : Fragment(R.layout.fragment_basket) {
    private val binding by viewBinding(FragmentBasketBinding::bind)
    private val viewModel by viewModels<BasketViewModel>()
    private val basketAdapter = BasketAdapter(
        onDeleteClickListener = ::onDeleteClickListener,
        onMinusClickListener = ::onMinusClickListener,
        onPlusClickListener = ::onPlusClickListener
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initView()
        viewModel.getEntityFromRoom()
    }

    private fun observe() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is BasketState.Loading -> {
                    with(binding) {
                        progressBar.visible()
                        textViewErrorMessage.gone()
                        recyclerView.gone()
                    }
                }
                is BasketState.Error -> {
                    with(binding) {
                        progressBar.gone()
                        textViewErrorMessage.visible()
                        recyclerView.gone()
                    }
                }
                is BasketState.Success -> {
                    basketAdapter.basketList = it.entities
                    basketUi(entities = it.entities)

                    with(binding) {
                        progressBar.gone()
                        textViewErrorMessage.gone()
                        recyclerView.visible()
                    }
                }
            }
        }
    }

    private fun initView() {
        binding.recyclerView.adapter = basketAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onMinusClickListener(entity: BasketEntity) {
        if (entity.itemCount == 1) {
            onDeleteClickListener(entity = entity)
        } else {
            viewModel.updateEntityFromRoom(entity = entity.copy(itemCount = entity.itemCount-1))
        }
    }

    private fun onPlusClickListener(entity: BasketEntity) {
        viewModel.updateEntityFromRoom(entity = entity.copy(itemCount = entity.itemCount+1))
    }

    private fun onDeleteClickListener(entity: BasketEntity) {

        val alert = MaterialAlertDialogBuilder(requireContext())
        alert.setTitle("Uyarı")
        alert.setMessage("Urunu silmek istediğinizden emin misiniz?")
        alert.setPositiveButton("Evet") { _, _ ->
            viewModel.deleteEntityFromRoom(entity = entity)
        }
        alert.setNegativeButton("Hayır") { _, _ ->
        }
        alert.show()
    }

    private fun basketUi(entities: List<BasketEntity>) {
        var totalAmount = 0f
        entities.forEach {
            totalAmount += it.itemCount.toFloat() * it.price.toFloat()
        }
        binding.textViewTotalAmount.text = "${totalAmount}$"

        binding.buttonBuyNow.setOnClickListener {
            Toast.makeText(requireContext(),"Siparisiniz Yola Cıkmıstır",Toast.LENGTH_SHORT).show()
            viewModel.deleteAllEntityFromRoom()
        }
    }

}
