package com.and.filmku.viewModel

import com.and.filmku.model.ResponseDataFilm
import com.and.filmku.model.ResultFilm
import com.and.filmku.network.RestfulApi
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilmViewModelTest {
    private lateinit var api: RestfulApi
    private lateinit var viewModel: FilmViewModel

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
        every {
            runBlocking {
                api.getAllFilm()
            }
        } returns callMock

        every {
            callMock.enqueue(any())
        } answers {
            val callback = firstArg<Callback<ResponseDataFilm>>()
            callback.onResponse(callMock, Response.success(responseData))
        }

        // Memanggil fungsi yang akan diuji
        viewModel.callApiFilm()

        // Verifikasi pemanggilan api.getAllFilm()
        verify {
            runBlocking {
                api.getAllFilm()
            }
            callMock.enqueue(any())
        }

        // Memverifikasi bahwa hasil pemanggilan telah diterima dan di-set ke liveDataFilm
        assertEquals(responseData.results, viewModel.liveDataFilm.value)
    }

    @Test
    fun testCallApiFilm_ErrorResponse() = runBlocking {
        // Mock pemanggilan api.getAllFilm() yang menghasilkan response error
        val callMock = mockk<Call<ResponseDataFilm>>()
        every {
            runBlocking {
                api.getAllFilm()
            }
        } returns callMock

        every {
            callMock.enqueue(any())
        } answers {
            val callback = firstArg<Callback<ResponseDataFilm>>()
            callback.onFailure(callMock, Exception("Error fetching film data"))
        }

        // Memanggil fungsi yang akan diuji
        viewModel.callApiFilm()

        // Verifikasi pemanggilan api.getAllFilm()
        verify {
            runBlocking {
                api.getAllFilm()
            }
            callMock.enqueue(any())
        }

        // Memverifikasi bahwa liveDataFilm bernilai null karena terjadi error
        assertEquals(null, viewModel.liveDataFilm.value)
    }
}