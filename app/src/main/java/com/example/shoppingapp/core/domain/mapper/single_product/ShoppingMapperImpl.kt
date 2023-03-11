package com.example.shoppingapp.core.domain.mapper.single_product

import com.example.shoppingapp.core.common.orZero
import com.example.shoppingapp.core.data.source.remote.model.ProductDto
import com.example.shoppingapp.core.data.source.remote.model.Rating
import com.example.shoppingapp.core.domain.mapper.ShoppingMapper
import com.example.shoppingapp.core.domain.domain_model.DomainModel
import javax.inject.Inject

class ShoppingMapperImpl @Inject constructor(): ShoppingMapper<ProductDto,DomainModel> {
    override fun map(input: ProductDto?): DomainModel {
        return  DomainModel(
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