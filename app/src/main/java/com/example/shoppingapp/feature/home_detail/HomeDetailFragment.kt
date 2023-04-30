package com.example.shoppingapp.feature.home_detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shoppingapp.R
import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.common.loadImage
import com.example.shoppingapp.core.common.viewBinding
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.data.source.local.RatingEntity
import com.example.shoppingapp.core.domain.domain_model.DomainModel
import com.example.shoppingapp.databinding.FragmentHomeDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeDetailFragment : Fragment(R.layout.fragment_home_detail) {

    private val binding by viewBinding(FragmentHomeDetailBinding::bind)
    private val viewModel by viewModels<HomeDetailViewModel>()
    private val args: HomeDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        initView()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.singleProduct
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            with(binding) {}
                        }
                        is Resource.Error -> {
                            with(binding) {}
                        }
                        is Resource.Success -> {
                            singleProductData(product = it.data)
                            with(binding) {}
                        }
                    }
                }
        }
    }

    private fun initView() {
        val id = args.id
        viewModel.getSingleProduct(id = id)
        popBackStackEvent()
    }
    private fun popBackStackEvent() {
        binding.imageButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun singleProductData(product: DomainModel) {
        with(binding) {
            imageViewProductImage.loadImage(product.image)
            textViewProductCategoryName.text = product.category
            textViewProductDescription.text = product.description
            textViewProductPrice.text = "${product.price}$"

            buttonAddToBag.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.added_basket),
                    Toast.LENGTH_SHORT
                ).show()

                viewModel.insertEntity(
                    entity = BasketEntity(
                        category = product.category,
                        description = product.description,
                        id = product.id,
                        image = product.image,
                        price = product.price,
                        rating = RatingEntity(
                            count = product.rating.count,
                            rate = product.rating.rate
                        ),
                        title = product.title
                    )
                )
            }
        }
    }
}