package com.and.filmku.network

import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class RetrofitClientTest {

    @Mock
    private lateinit var retrofit: Retrofit

    @Mock
    private lateinit var restfulApi: RestfulApi

    @InjectMocks
    private lateinit var retrofitClient: RetrofitClient

    @Before
    fun setUp() {
        retrofit = Retrofit.Builder()
            .baseUrl(RetrofitClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()

        retrofitClient = RetrofitClient
    }


    @Test
    fun testProvideNewsApi() {
        val result = retrofitClient.provideNewsApi(retrofit)

        assertNotNull(result)
        val proxyInterface = result.javaClass.interfaces.firstOrNull()
        assertEquals(RestfulApi::class.java, proxyInterface)
        // Lakukan pengujian lain sesuai kebutuhan
    }
}
