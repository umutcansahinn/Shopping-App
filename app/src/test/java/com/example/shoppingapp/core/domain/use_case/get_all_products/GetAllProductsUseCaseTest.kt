package com.example.shoppingapp.core.domain.use_case.get_all_products

import app.cash.turbine.test
import com.example.shoppingapp.core.common.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetAllProductsUseCaseTest {

    private lateinit var getAllProductsUseCase: FakeGetAllProductsUseCase

    @Before
    fun setup() {
        getAllProductsUseCase = FakeGetAllProductsUseCase()
    }

    @Test
    fun networkState_whenStateLoading_returnLoading() = runBlocking {
        getAllProductsUseCase().test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun networkState_whenStateLoadingAndSuccess_returnLoadingAndSuccessSequentially() {
        runBlocking {
            getAllProductsUseCase().test {
                assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndError_returnError() {
        runBlocking {
            getAllProductsUseCase.updateShowError(true)
            getAllProductsUseCase().test {
                assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
                awaitComplete()
            }
        }
    }
}