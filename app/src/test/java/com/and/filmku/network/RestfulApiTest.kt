package com.and.filmku.network

import com.and.filmku.model.ResponseDataFilm
import com.and.filmku.model.ResultFilm
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class RestfulApiTest {
    private lateinit var api: RestfulApi

    @Before
    fun setUp() {
        api = mockk()
    }

    @Test
    fun testGetAllFilm_SuccessfulResponse() = runBlocking {
        // Persiapan data untuk pengujian
        val responseData = ResponseDataFilm(
            page = 1,
            totalPages = 2,
            totalResults = 20,
            results = listOf(
                ResultFilm(
                    adult = false,
                    backdropPath = "backdrop_path",
                    genreIds = listOf(1, 2, 3),
                    id = 123,
                    originalLanguage = "en",
                    originalTitle = "Original Title",
                    overview = "Overview",
                    popularity = 4.5,
                    posterPath = "poster_path",
                    releaseDate = "2022-01-01",
                    title = "Title",
                    video = false,
                    voteAverage = 7.8,
                    voteCount = 1000
                )
            )
        )

        // Mock pemanggilan api.getAllFilm()
        val callMock = mockk<Call<ResponseDataFilm>>()
        coEvery { callMock.execute() } returns Response.success(responseData)

        every { api.getAllFilm() } returns callMock

        // Memanggil fungsi yang akan diuji
        val result = api.getAllFilm().execute()

        // Memverifikasi bahwa hasil pemanggilan adalah respons sukses dengan data yang sesuai
        assertEquals(responseData, result.body())
    }

}
