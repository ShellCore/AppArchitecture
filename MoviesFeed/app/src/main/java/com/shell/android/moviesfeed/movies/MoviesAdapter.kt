package com.shell.android.moviesfeed.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.shell.android.moviesfeed.R

class MoviesAdapter(var moviesModels: ArrayList<MoviesModel>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(moviesModels[position])

    override fun getItemCount() = moviesModels.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var moviesModel: MoviesModel

        private lateinit var txtMovieName: TextView
        private lateinit var txtCountry: TextView
        private lateinit var imgMovie: ImageView

        fun bind(moviesModel: MoviesModel) {
            this.moviesModel = moviesModel
            initializeComponents()
            addMovieInformation()
        }

        private fun initializeComponents() {
            txtMovieName = itemView.findViewById(R.id.txtMovieName)
            txtCountry = itemView.findViewById(R.id.txtCountry)
            imgMovie = itemView.findViewById(R.id.imgMovie)
        }

        private fun addMovieInformation() {
            txtMovieName.text = moviesModel.title
            txtCountry.text = moviesModel.country
            loadMovieImage()
        }

        private fun loadMovieImage() {
            Glide.with(itemView.context)
                .load(moviesModel.photoUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imgMovie)
        }
    }
}
