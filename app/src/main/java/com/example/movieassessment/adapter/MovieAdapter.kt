package com.example.movieassessment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieassessment.databinding.MovieListItemBinding
import com.example.notesapproom.model.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(val itemBinding: MovieListItemBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.year == newItem.year &&
                    oldItem.type == newItem.type &&
                    oldItem.imdbID == newItem.imdbID &&
                    oldItem.poster == newItem.poster
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = differ.currentList[position]

        holder.itemBinding.movieTitleTextView.text = currentMovie.title.toString()
        Glide.with(holder.itemBinding.movieImageView.context)
            .load(currentMovie.poster)
            .into(holder.itemBinding.movieImageView)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}