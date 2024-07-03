package com.slayer.movie_details.domain.usecases

import com.slayer.movie_details.domain.repositories.MovieDetailsRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repo: MovieDetailsRepository) {
    suspend operator fun invoke(id : Int) = repo.getMovieDetails(id)
}