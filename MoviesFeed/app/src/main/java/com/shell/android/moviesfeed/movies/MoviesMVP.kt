package com.shell.android.moviesfeed.movies

import io.reactivex.Observable

interface MoviesMVP {

    interface View {
        fun updateData(viewModel: MoviesViewModel)
        fun showSnackbar(message: String)
    }

    interface Presenter {
        fun loadData()
        fun rxJavaUnsubscribe()
        fun setView(view: MoviesMVP.View)
    }

    interface Model {
        fun result(): Observable<MoviesViewModel>
    }
}