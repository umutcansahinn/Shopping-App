package com.example.shoppingapp.core.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
data class BasketEntity(

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "description")
    val description: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "price")
    val price: Double,

    @Embedded
    val rating: RatingEntity,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "item_count")
    val itemCount: Int = 1
)

@Entity(tableName = "rating")
data class RatingEntity(
    val count: Int,
    val rate: Double
)
