package com.slayer.movie_details.domain.usecases

import com.slayer.movie_details.domain.repositories.MovieDetailsRepository
import javax.inject.Inject

class GetCastUseCase @Inject constructor(private val repo: MovieDetailsRepository) {
    suspend operator fun invoke(id : Int) = repo.getMovieCasts(id)
}