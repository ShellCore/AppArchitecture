package com.shell.android.moviesfeed.movies

import com.shell.android.moviesfeed.http.MovieInfoService
import com.shell.android.moviesfeed.http.MoviesApiServices
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesModule {

    @Provides
    fun getMoviesPresenter(model: MoviesMVP.Model): MoviesMVP.Presenter {
        return PresenterImpl(model)
    }

    @Provides
    fun getMoviesModel(repository: MoviesRepository): MoviesMVP.Model {
        return ModelImpl(repository)
    }

    @Provides
    @Singleton
    fun getMoviesRepository(
        moviesService: MoviesApiServices,
        infoService: MovieInfoService
    ): MoviesRepository {
        return MoviesRepositoryImpl(moviesService, infoService)
    }
}