package com.shell.android.moviesfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shell.android.moviesfeed.movies.MoviesMVP
import com.shell.android.moviesfeed.movies.MoviesViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MoviesMVP.View {

    companion object {
        const val TAG: String = "MainActivity"
    }

    @Inject
    private lateinit var presenter: MoviesMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupInjection()
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.loadData()
    }

    override fun updateData(viewModel: MoviesViewModel) {
        // TODO Falta implementación
    }

    override fun showSnackbar(message: String) {
        // TODO Falta implementación
    }

    private fun setupInjection() {
        (application as MovieFeedApplication).component.inject(this)
    }
}
