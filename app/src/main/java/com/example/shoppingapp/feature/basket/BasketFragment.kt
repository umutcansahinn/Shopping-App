package com.example.shoppingapp.feature.basket

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.common.gone
import com.example.shoppingapp.core.common.viewBinding
import com.example.shoppingapp.core.common.visible
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.databinding.FragmentBasketBinding
import com.example.shoppingapp.feature.basket.adapter.BasketAdapter
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
        observeData()
        initView()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.basketList
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            with(binding) {
                                progressBar.visible()
                                textViewErrorMessage.gone()
                                recyclerView.gone()
                                emptyListView.gone()
                            }
                        }
                        is Resource.Error -> {
                            with(binding) {
                                textViewErrorMessage.text = it.errorMessage
                                progressBar.gone()
                                textViewErrorMessage.visible()
                                recyclerView.gone()
                                emptyListView.gone()
                            }
                        }
                        is Resource.Success -> {
                            it.data.collect { list ->
                                if (list.isNotEmpty()) {
                                    basketAdapter.submitList(list)
                                    basketUi(entities = list)

                                    with(binding) {
                                        progressBar.gone()
                                        textViewErrorMessage.gone()
                                        emptyListView.gone()
                                        recyclerView.visible()
                                    }
                                } else {
                                    with(binding) {
                                        emptyListView.visible()

                                        progressBar.gone()
                                        textViewErrorMessage.gone()
                                        recyclerView.gone()
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
    }

    private fun onPlusOrMinusClickListener(entity: BasketEntity, isPlus: Boolean) {
        viewModel.updateEntityFromRoom(entity = entity, isPlus = isPlus)
    }

    private fun onDeleteClickListener(entity: BasketEntity) {
        viewModel.deleteEntityFromRoom(entity = entity)
    }


    @SuppressLint("SetTextI18n")
    private fun basketUi(entities: List<BasketEntity>) {
        var totalAmount = 0f
        entities.forEach {
            totalAmount += it.itemCount.toFloat() * it.price.toFloat()
        }
        binding.textViewTotalAmount.text = "${totalAmount}$"
        binding.buttonBuyNow.setOnClickListener {
            if (entities.isNotEmpty()) {
                findNavController().navigate(R.id.action_navigation_basket_to_navigation_payment)
            }
        }
    }
}
