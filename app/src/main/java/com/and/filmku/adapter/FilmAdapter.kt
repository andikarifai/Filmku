package com.and.filmku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.and.filmku.databinding.ItemFilmBinding
import com.and.filmku.model.ResponseDataFilm
import com.and.filmku.model.ResponseFilmItem
import com.bumptech.glide.Glide

class FilmAdapter(var listFilm : List<ResponseDataFilm>) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemFilmBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitleFilm.text = listFilm[position].title
        holder.binding.tvRelease.text = listFilm[position].releaseDate
        Glide.with(holder.itemView).load(listFilm[position].posterPath).into(holder.binding.ivLogoFilm)
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }
}