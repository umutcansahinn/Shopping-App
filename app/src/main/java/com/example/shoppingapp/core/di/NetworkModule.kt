package com.example.shoppingapp.core.di

import com.example.shoppingapp.core.common.Constants.BASE_URL
import com.example.shoppingapp.core.data.source.remote.api.ShoppingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideShoppingService(): ShoppingApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShoppingApi::class.java)
    }
}