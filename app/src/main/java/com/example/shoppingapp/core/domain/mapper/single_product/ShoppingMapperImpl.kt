package com.example.shoppingapp.core.domain.mapper.single_product

import com.example.shoppingapp.core.common.orZero
import com.example.shoppingapp.core.data.model.ProductDto
import com.example.shoppingapp.core.data.model.Rating
import com.example.shoppingapp.core.domain.mapper.ShoppingMapper
import com.example.shoppingapp.core.domain.modelUi.ProductUiModel
import javax.inject.Inject

class ShoppingMapperImpl @Inject constructor(): ShoppingMapper<ProductDto,ProductUiModel> {
    override fun map(input: ProductDto?): ProductUiModel {
        return  ProductUiModel(
            category = input?.category.orEmpty(),
            description = input?.description.orEmpty(),
            id = input?.id.orZero(),
            image = input?.image.orEmpty(),
            price = input?.price.orZero(),
            rating = Rating(
                count = input?.rating?.count.orZero(),
                rate = input?.rating?.rate.orZero()
            ),
            title = input?.title.orEmpty()
        )
    }
}