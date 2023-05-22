package com.and.filmku.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.and.filmku.R
import com.and.filmku.databinding.ItemFilmBinding
import com.and.filmku.model.ResultFilm
import com.bumptech.glide.Glide

class FilmAdapter(private var listFilm: List<ResultFilm>,
                  private var onItemClick: (ResultFilm) -> Unit
) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick.invoke(listFilm[adapterPosition])
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitleFilm.text = listFilm[position].title
        holder.binding.tvRelease.text = listFilm[position].releaseDate
        val baseUrl = "https://image.tmdb.org/t/p/w500"
        val imageUrl = baseUrl + listFilm[position].backdropPath

        Glide.with(holder.itemView)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.ivLogoFilm)

    }

    override fun getItemCount(): Int {
        return listFilm.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(films: List<ResultFilm>) {
        listFilm = films
//        this.onItemClick = onItemClick
        notifyDataSetChanged()
    }


}


