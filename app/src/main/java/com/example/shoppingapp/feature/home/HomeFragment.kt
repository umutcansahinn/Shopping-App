package com.example.shoppingapp.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.common.gone
import com.example.shoppingapp.core.common.viewBinding
import com.example.shoppingapp.core.common.visible
import com.example.shoppingapp.databinding.FragmentHomeBinding
import com.example.shoppingapp.feature.home.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()
    private val productsAdapter = ProductsAdapter(::itemSetClick, arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        initView()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productList
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
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
                            with(binding) {
                                productsAdapter.updateList(it.data)
                                progressBar.gone()
                                textViewErrorMessage.gone()
                                recyclerView.visible()
                            }
                        }
                    }
                }
        }
    }


    private fun initView() {
        binding.recyclerView.adapter = productsAdapter
    }

    private fun itemSetClick(id: Int) {
        val action = HomeFragmentDirections.actionNavigationHomeToNavigationHomeDetail(id = id)
        findNavController().navigate(action)
    }
}