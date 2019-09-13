package com.shell.android.moviesfeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.shell.android.moviesfeed.movies.MoviesAdapter
import com.shell.android.moviesfeed.movies.MoviesMVP
import com.shell.android.moviesfeed.movies.MoviesModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MoviesMVP.View {

    @Inject
    lateinit var presenter: MoviesMVP.Presenter

    private lateinit var moviesAdapter: MoviesAdapter
    private var moviesModels: ArrayList<MoviesModel> = ArrayList()

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
        moviesAdapter = MoviesAdapter(moviesModels)
        recMovies.apply {
            adapter = moviesAdapter
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.loadData()
    }

    override fun onStop() {
        super.onStop()
        presenter.rxJavaUnsubscribe()
        moviesModels.clear()
        moviesAdapter.notifyDataSetChanged()
    }

    override fun updateData(viewModel: MoviesModel) {
        moviesModels.add(viewModel)
        moviesAdapter.notifyItemInserted(moviesModels.size - 1)
    }

    override fun showSnackbar(message: String) {
        Snackbar.make(mainContainer, message, Snackbar.LENGTH_SHORT).show()

    }
}
