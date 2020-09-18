package com.github.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.viewmodel.databinding.ItemPhotoBinding
import com.github.viewmodel.model.PhotoItem

class PhotosAdapter :
    ListAdapter<PhotoItem, RecyclerView.ViewHolder>(PhotoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhotoHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val photo = getItem(position)
        (holder as PhotoHolder).bind(photo)
    }

    inner class PhotoHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(data: PhotoItem) {
            binding.apply {
                item = data
                executePendingBindings()
            }
        }
    }

}

private class PhotoDiffCallback : DiffUtil.ItemCallback<PhotoItem>() {

    override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem == newItem
    }
}