package com.example.shoppingapp.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BasketEntity::class], version = 1)
abstract class BasketDatabase: RoomDatabase() {
    abstract fun basketDao(): BasketDao
}