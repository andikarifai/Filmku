package com.and.filmku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.and.filmku.databinding.ItemFavoriteBinding
import com.and.filmku.room.FavoriteFilm
import com.bumptech.glide.Glide

class FavoriteAdapter (var favoriteFilms: List<FavoriteFilm>) :
RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoriteFilm = favoriteFilms[position]
        holder.bind(favoriteFilm)
    }

    override fun getItemCount(): Int {
        return favoriteFilms.size
    }

    inner class FavoriteViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favoriteFilm: FavoriteFilm) {
            binding.favImage.setImageDrawable(null) // Clear previous image
            Glide.with(itemView)
                .load(favoriteFilm.backdropPath)
                .into(binding.favImage)
            binding.tvTitleFilm.text = favoriteFilm.title
            binding.tvRelease.text = favoriteFilm.releaseDate
        }
    }
}