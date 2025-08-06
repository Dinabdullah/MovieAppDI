package com.example.movieapp.domain.usecase.moviedetails

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.moviedetailsrepo.IMovieDetailsRepo

class GetMovieDetailsUseCaseImpl(
    private val movieDetails: IMovieDetailsRepo
) : GetMovieDetailsUseCase {
    override suspend fun invoke(movieId: Int): Result<Movie> {
        return movieDetails.getMovieDetails(movieId)
    }
}