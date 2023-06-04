package com.and.filmku.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.and.filmku.room.FavoriteDao
import com.and.filmku.room.FavoriteFilm
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

@Suppress("DEPRECATION")
@ExperimentalCoroutinesApi
class FavoriteViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var favoriteDao: FavoriteDao

    private lateinit var favoriteViewModel: FavoriteViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        favoriteViewModel = FavoriteViewModel(favoriteDao)
    }


    @Test
    fun testAddFavoriteFilm() = runBlockingTest {
        val favoriteFilm = FavoriteFilm(
            id = 1,
            backdropPath = "backdrop_path",
            overview = "overview",
            releaseDate = "2023-05-26",
            title = "Film 1"
        )

        favoriteViewModel.addFavoriteFilm(favoriteFilm)

        // Verify that favoriteDao.addFavoriteFilm() is called
        coVerify { favoriteDao.addFavoriteFilm(favoriteFilm) }
    }

    @Test
    fun testDelete() = runBlockingTest {
        val favoriteFilm = FavoriteFilm(
            id = 1,
            backdropPath = "backdrop_path",
            overview = "overview",
            releaseDate = "2023-05-26",
            title = "Film 1"
        )

        favoriteViewModel.delete(favoriteFilm)

        // Verify that favoriteDao.deleteFavoriteFilm() is called
        coVerify { favoriteDao.deleteFavoriteFilm(favoriteFilm) }
    }
}
