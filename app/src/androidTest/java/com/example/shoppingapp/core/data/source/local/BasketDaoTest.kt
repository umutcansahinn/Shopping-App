package com.example.shoppingapp.core.data.source.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class BasketDaoTest {

    private lateinit var database: BasketDatabase
    private lateinit var dao: BasketDao

    private lateinit var ratingEntity: RatingEntity
    private lateinit var entity: BasketEntity

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BasketDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.basketDao()

        ratingEntity = RatingEntity(0, 0.0)
        entity = BasketEntity(
            "",
            "",
            1,
            "",
            0.0,
            ratingEntity,
            "",
            0
        )

    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertBasketEntityTest() = runBlocking {
        dao.insertBasketEntity(entity)
        val result = dao.getAllBasketEntities().first()
        assertThat(result).contains(entity)
    }

    @Test
    fun deleteBasketEntityTest() = runBlocking {
        dao.insertBasketEntity(entity)
        dao.deleteBasketEntity(entity)
        val result = dao.getAllBasketEntities().first()
        assertThat(result).doesNotContain(entity)
    }

    @Test
    fun deleteAllBasketEntityTest() = runBlocking {
        val entity2 = BasketEntity(
            "",
            "",
            2,
            "",
            0.0,
            ratingEntity,
            "",
            1
        )
        dao.insertBasketEntity(entity)
        dao.insertBasketEntity(entity2)
        dao.deleteAllBasketEntities()
        val result = dao.getAllBasketEntities().first()
        assertThat(result).doesNotContain(entity)
    }

    @Test
    fun updateBasketEntityTest() = runBlocking {
        dao.insertBasketEntity(entity)
        val updateEntity = entity.copy(description = "updated")
        dao.updateBasketEntity(updateEntity)
        val result = dao.getAllBasketEntities().first()
        assertThat(result).contains(updateEntity)

    }

    @Test
    fun getAllEntitiesTest() = runBlocking {
        val result = dao.getAllBasketEntities().first()
        assertThat(result).isEmpty()
    }

    @Test
    fun getAllEntitiesTest2() = runBlocking {
        dao.insertBasketEntity(entity)
        val result = dao.getAllBasketEntities().first()
        assertThat(result).hasSize(1)
    }

    @Test
    fun getAllEntitiesTest3() = runBlocking {
        dao.insertBasketEntity(entity)
        val entity2 = entity.copy(id = 2)
        dao.insertBasketEntity(entity2)
        val result = dao.getAllBasketEntities().first()
        assertThat(result).hasSize(2)
    }
}