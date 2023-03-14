package com.example.shoppingapp.core.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket ORDER BY id DESC")
    fun getAllBasketEntities(): Flow<List<BasketEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBasketEntity(entity: BasketEntity)

    @Delete
    suspend fun deleteBasketEntity(entity: BasketEntity)

    @Query("DELETE FROM basket")
    suspend fun deleteAllBasketEntities()

    @Update
    suspend fun updateBasketEntity(entity: BasketEntity)
}