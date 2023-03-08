package com.example.shoppingapp.feature.home_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shoppingapp.R
import com.example.shoppingapp.core.common.loadImage
import com.example.shoppingapp.core.common.viewBinding
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.data.source.local.RatingEntity
import com.example.shoppingapp.core.domain.modelUi.ProductUiModel
import com.example.shoppingapp.databinding.FragmentHomeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailFragment : Fragment(R.layout.fragment_home_detail) {

    private val binding by viewBinding(FragmentHomeDetailBinding::bind)
    private val viewModel by viewModels<HomeDetailViewModel>()
    private val args: HomeDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initView()
    }

    private fun observe() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is SingleProductState.Loading -> {
                    with(binding) {

                    }
                }
                is SingleProductState.Error -> {
                    with(binding) {
                    }
                }
                is SingleProductState.Success -> {
                    singleProductData(product = it.products)
                    with(binding) {

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

    @SuppressLint("SetTextI18n")
    private fun singleProductData(product: ProductUiModel) {
        with(binding) {
            imageViewProductImage.loadImage(product.image)
            textViewProductCategoryName.text = product.category
            textViewProductDescription.text = product.description
            textViewProductPrice.text = "${product.price}$"

            buttonAddToBag.setOnClickListener {
                viewModel.addEntityBasket(
                    entity = BasketEntity(
                        category = product.category,
                        description = product.description,
                        id = product.id,
                        image = product.image,
                        price = product.price,
                        rating = RatingEntity(
                            count = product.rating.count,
                            rate = product.rating.rate
                        ) ,
                        title = product.title
                    )
                )
            }
        }
    }
}