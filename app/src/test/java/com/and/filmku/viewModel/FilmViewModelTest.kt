package com.and.filmku.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.and.filmku.model.ResponseDataFilm
import com.and.filmku.model.ResultFilm
import com.and.filmku.network.RestfulApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModelTest {
    private lateinit var api: RestfulApi
    private lateinit var viewModel: FilmViewModel
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        api = mockk()
        viewModel = FilmViewModel(api)
    }

    @Test
    fun testCallApiFilm_SuccessfulResponse() = runBlocking {
        // Persiapan data untuk pengujian
        val responseData = ResponseDataFilm(
            page = 1,
            totalPages = 2,
            totalResults = 20,
            results = listOf(
                ResultFilm(
                    // Data film
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

        // Mock pemanggilan api.getAllFilm() dengan respons palsu
        val callMock = mockk<Call<ResponseDataFilm>>()
        coEvery { api.getAllFilm() } returns callMock

        val responseMock = mockk<Response<ResponseDataFilm>>()
        every { responseMock.isSuccessful } returns true
        every { responseMock.body() } returns responseData

        coEvery { callMock.enqueue(any()) } answers {
            val callback = firstArg<Callback<ResponseDataFilm>>()
            callback.onResponse(callMock, responseMock)
        }

        // Memanggil fungsi yang akan diuji
        viewModel.callApiFilm()

        // Verifikasi pemanggilan api.getAllFilm()
        coVerify { api.getAllFilm() }
        verify { callMock.enqueue(any()) }

        // Memverifikasi bahwa hasil pemanggilan telah diterima dan di-set ke liveDataFilm
        assertEquals(responseData.results, viewModel.liveDataFilm.value)
    }

    @Test
    fun testCallApiFilm_ErrorResponse() = runBlocking {
        // Mock pemanggilan api.getAllFilm() yang menghasilkan response error
        val callMock = mockk<Call<ResponseDataFilm>>()
        coEvery { api.getAllFilm() } returns callMock

        val throwable = mockk<Throwable>()
        coEvery { callMock.enqueue(any()) } answers {
            val callback = firstArg<Callback<ResponseDataFilm>>()
            callback.onFailure(callMock, throwable)
        }

        // Memanggil fungsi yang akan diuji
        viewModel.callApiFilm()

        // Verifikasi pemanggilan api.getAllFilm()
        coVerify { api.getAllFilm() }
        verify { callMock.enqueue(any()) }

        // Memverifikasi bahwa liveDataFilm bernilai null karena terjadi error
        assertEquals(null, viewModel.liveDataFilm.value)
    }
}
