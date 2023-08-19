package com.example.shoppingapp.core.domain.use_case.single_product

import app.cash.turbine.test
import com.example.shoppingapp.core.common.Resource
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSingleProductUseCaseTest {

    private lateinit var getSingleProductUseCase: FakeGetSingleProductUseCase

    @Before
    fun setup() {
        getSingleProductUseCase = FakeGetSingleProductUseCase()
    }

    @Test
    fun networkState_whenStateLoading_returnLoading() = runBlocking {
        getSingleProductUseCase(1).test {
            Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun networkState_whenStateLoadingAndSuccess_returnLoadingAndSuccessSequentially() {
        runBlocking {
            getSingleProductUseCase(1).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndError_returnError() {
        runBlocking {
            getSingleProductUseCase.updateShowError(true)
            getSingleProductUseCase(1).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
                awaitComplete()
            }
        }
    }
}