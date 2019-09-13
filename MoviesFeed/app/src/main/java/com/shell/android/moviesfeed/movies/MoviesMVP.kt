package com.shell.android.moviesfeed.movies

import io.reactivex.Observable

interface MoviesMVP {

    interface View {
        fun updateData(viewModel: MoviesModel)
        fun showSnackbar(message: String)
    }

    interface Presenter {
        fun loadData()
        fun rxJavaUnsubscribe()
        fun setView(view: View)
    }

    interface Model {
        fun result(): Observable<MoviesModel>
    }
}