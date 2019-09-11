package com.shell.android.moviesfeed.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.shell.android.moviesfeed.Movie
import com.shell.android.moviesfeed.R

class MoviesAdapter(var movies: ArrayList<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])

    override fun getItemCount() = movies.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var movie: Movie

        private lateinit var txtMovieName: TextView
        private lateinit var txtCountry: TextView
        private lateinit var imgMovie: ImageView

        fun bind(movie: Movie) {
            this.movie = movie
            initializeComponents()
            addMovieInformation()
        }

        private fun initializeComponents() {
            txtMovieName = itemView.findViewById(R.id.txtMovieName)
            txtCountry = itemView.findViewById(R.id.txtCountry)
            imgMovie = itemView.findViewById(R.id.imgMovie)
        }

        private fun addMovieInformation() {
            txtMovieName.text = movie.title
            txtCountry.text = movie.country
            loadMovieImage()
        }

        private fun loadMovieImage() {
            Glide.with(itemView.context)
                .load(movie.photoUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imgMovie)
        }
    }
}
