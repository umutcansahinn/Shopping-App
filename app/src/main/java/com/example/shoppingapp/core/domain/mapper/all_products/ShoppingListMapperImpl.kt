package com.example.shoppingapp.core.domain.mapper.all_products

import com.example.shoppingapp.core.common.orZero
import com.example.shoppingapp.core.data.source.remote.model.ProductDto
import com.example.shoppingapp.core.data.source.remote.model.Rating
import com.example.shoppingapp.core.domain.mapper.ShoppingListMapper
import com.example.shoppingapp.core.domain.domain_model.DomainModel
import javax.inject.Inject

class ShoppingListMapperImpl @Inject
constructor() : ShoppingListMapper<ProductDto, DomainModel> {

    override fun map(input: List<ProductDto>?): List<DomainModel> {
        return input?.map {
            DomainModel(
                category = it.category.orEmpty(),
                description = it.description.orEmpty(),
                id = it.id.orZero(),
                image = it.image.orEmpty(),
                price = it.price.orZero(),
                rating = Rating(
                    count = it.rating?.count.orZero(),
                    rate = it.rating?.rate.orZero()
                ),
                title = it.title.orEmpty()
            )
        } ?: emptyList()
    }
}


