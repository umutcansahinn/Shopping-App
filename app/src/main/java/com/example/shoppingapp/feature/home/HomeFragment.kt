package com.example.shoppingapp.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.core.common.gone
import com.example.shoppingapp.core.common.viewBinding
import com.example.shoppingapp.core.common.visible
import com.example.shoppingapp.databinding.FragmentHomeBinding
import com.example.shoppingapp.feature.home.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()
    private val productsAdapter = ProductsAdapter(::itemSetClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllProducts()
        observe()
        initView()
    }

    private fun observe() {
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is AllProductsState.Loading-> {
                    with(binding) {
                        progressBar.visible()
                        textViewErrorMessage.gone()
                        recyclerView.gone()
                    }
                }
                is AllProductsState.Error-> {
                    with(binding) {
                        progressBar.gone()
                        textViewErrorMessage.visible()
                        recyclerView.gone()
                    }
                }
                is AllProductsState.Success-> {
                    productsAdapter.productsList = it.products
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
        binding.recyclerView.adapter = productsAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        //binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun itemSetClick(id: Int) {
        val action = HomeFragmentDirections.actionNavigationHomeToHomeDetailFragment(id = id)
        findNavController().navigate(action)
    }
}