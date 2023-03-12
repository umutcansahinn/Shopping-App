package com.example.shoppingapp.feature.basket

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.common.gone
import com.example.shoppingapp.core.common.viewBinding
import com.example.shoppingapp.core.common.visible
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.databinding.FragmentBasketBinding
import com.example.shoppingapp.feature.basket.adapter.BasketAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BasketFragment : Fragment(R.layout.fragment_basket) {

    private val binding by viewBinding(FragmentBasketBinding::bind)
    private val viewModel by viewModels<BasketViewModel>()
    private val basketAdapter = BasketAdapter(
        onDeleteClickListener = ::onDeleteClickListener,
        onPlusOrMinusClickListener = ::onPlusOrMinusClickListener
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initView()
        viewModel.getEntityFromRoom()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is Resource.Loading -> {
                            with(binding) {
                                progressBar.visible()
                                textViewErrorMessage.gone()
                                recyclerView.gone()
                            }
                        }
                        is Resource.Error -> {
                            with(binding) {
                                textViewErrorMessage.text = it.errorMessage
                                progressBar.gone()
                                textViewErrorMessage.visible()
                                recyclerView.gone()
                            }
                        }
                        is Resource.Success -> {
                            it.data.collect { list ->
                                basketAdapter.basketList = list
                                basketUi(entities = list)

                                with(binding) {
                                    progressBar.gone()
                                    textViewErrorMessage.gone()
                                    recyclerView.visible()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initView() {
        binding.recyclerView.adapter = basketAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onPlusOrMinusClickListener(entity: BasketEntity, isPlus: Boolean) {
        viewModel.updateEntityFromRoom(entity = entity, isPlus = isPlus)
    }

    private fun onDeleteClickListener(entity: BasketEntity) {
        viewModel.deleteEntityFromRoom(entity = entity)
    }

    private fun basketUi(entities: List<BasketEntity>) {
        var totalAmount = 0f
        entities.forEach {
            totalAmount += it.itemCount.toFloat() * it.price.toFloat()
        }
        binding.textViewTotalAmount.text = "${totalAmount}$"

        binding.buttonBuyNow.setOnClickListener {
            Toast.makeText(requireContext(), "Siparisiniz Yola Cıkmıstır", Toast.LENGTH_SHORT)
                .show()
            viewModel.deleteAllEntityFromRoom()
        }
    }
}
