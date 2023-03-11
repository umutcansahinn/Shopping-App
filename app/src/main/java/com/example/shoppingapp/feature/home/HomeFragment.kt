package com.example.shoppingapp.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
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
    private val productsAdapter = ProductsAdapter(::itemSetClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllProducts()
        observe()
        initView()
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
                            productsAdapter.productsList = it.data

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

    private fun initView() {
        binding.recyclerView.adapter = productsAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun itemSetClick(id: Int) {
        val action = HomeFragmentDirections.actionNavigationHomeToHomeDetailFragment(id = id)
        findNavController().navigate(action)
    }
}