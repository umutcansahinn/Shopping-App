package com.example.shoppingapp.core.di

import android.content.Context
import androidx.room.Insert
import androidx.room.Room
import com.example.shoppingapp.core.data.source.local.BasketDao
import com.example.shoppingapp.core.data.source.local.BasketDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideBasketDatabase(@ApplicationContext context: Context): BasketDatabase {
        return Room.databaseBuilder(
            context,
            BasketDatabase::class.java,
            "basket_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBasketDao(basketDatabase: BasketDatabase): BasketDao {
        return basketDatabase.basketDao()
    }
}