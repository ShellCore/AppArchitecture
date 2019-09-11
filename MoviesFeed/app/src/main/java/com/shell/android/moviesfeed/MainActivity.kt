package com.shell.android.moviesfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.shell.android.moviesfeed.movies.MoviesAdapter
import com.shell.android.moviesfeed.movies.MoviesMVP
import com.shell.android.moviesfeed.movies.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MoviesMVP.View {

    companion object {
        const val TAG: String = "MainActivity"
    }

    @Inject
    private lateinit var presenter: MoviesMVP.Presenter

    private lateinit var moviesAdapter: MoviesAdapter
    private var movies: ArrayList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupInjection()
        setupMoviesAdapter()
    }

    private fun setupInjection() {
        (application as MovieFeedApplication).component.inject(this)
    }

    private fun setupMoviesAdapter() {
        moviesAdapter = MoviesAdapter(movies)
        recMovies.apply {
            adapter = moviesAdapter
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
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
}
