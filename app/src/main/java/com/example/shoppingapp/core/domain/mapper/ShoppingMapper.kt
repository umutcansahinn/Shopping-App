package com.example.shoppingapp.core.domain.mapper

interface ShoppingMapper<I,O> {

    fun map(input: I?): O
}