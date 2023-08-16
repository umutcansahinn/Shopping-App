package com.example.shoppingapp.core.data.source.remote.api

import com.example.shoppingapp.core.common.getAllProductsResponse
import com.example.shoppingapp.core.common.singleProductResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShoppingApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var shoppingApi: ShoppingApi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        shoppingApi = retrofit.create(ShoppingApi::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getAllProductsTest() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(getAllProductsResponse)
        mockWebServer.enqueue(mockResponse)

        val products = shoppingApi.getAllProducts()
        assertThat(products).isNotEmpty()
        assertThat(products[0].id).isEqualTo(1)
        assertThat(products[0].title).isEqualTo("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")
    }

    @Test
    fun getProductByIdTest() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(singleProductResponse)
        mockWebServer.enqueue(mockResponse)

        val singleProduct = shoppingApi.getProductById(1)
        assertThat(singleProduct.category).isEqualTo("men's clothing")
        assertThat(singleProduct.id).isEqualTo(1)
    }
}

