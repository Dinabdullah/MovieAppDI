package com.example.movieapp.data.remote

import com.example.movieapp.data.model.MovieDto
import com.example.movieapp.data.model.MovieResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MovieRemoteDataSourceImplTest {

    private lateinit var api: MovieApi
    private lateinit var dataSource: MovieRemoteDataSourceImpl

    @Before
    fun setup() {
        api = mockk()
        dataSource = MovieRemoteDataSourceImpl(api)
    }

    @Test
    fun `getPopularMovies returns filtered non-null movies`() = runTest {
        // Arrange
        val movies = listOf(
            MovieDto(id = 1, title = "Inception", overview = "", posterPath = ""),
            null,
            MovieDto(id = 2, title = "Interstellar", overview = "", posterPath = "")
        )
        coEvery { api.getMovies() } returns MovieResponse(
            page = 1,
            results = movies,
            totalPages = 1,
            totalResults = 2
        )

        // Act
        val result = dataSource.getPopularMovies()

        // Assert
        assertEquals(2, result.size)
        assertEquals("Inception", result[0].title)
        assertEquals("Interstellar", result[1].title)
    }

    @Test
    fun `getPopularMovies returns empty list when results is null`() = runTest {
        coEvery { api.getMovies() } returns MovieResponse(
            page = 1,
            results = null,
            totalPages = 1,
            totalResults = 0
        )

        val result = dataSource.getPopularMovies()

        assertEquals(emptyList<MovieDto>(), result)
    }

    @Test
    fun `getMovieDetails returns movie details from API`() = runTest {
        val movie = MovieDto(id = 10, title = "Tenet", overview = "", posterPath = "")
        coEvery { api.getMovieDetails(10) } returns movie

        val result = dataSource.getMovieDetails(10)

        assertEquals(movie, result)
    }
}
