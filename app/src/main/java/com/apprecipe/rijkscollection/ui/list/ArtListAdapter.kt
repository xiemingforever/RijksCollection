package com.apprecipe.rijkscollection.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.apprecipe.rijkscollection.R
import com.apprecipe.rijkscollection.data.ArtItem
import com.apprecipe.rijkscollection.databinding.ListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ArtListAdapter(private val clickListener: (String) -> Unit) :
    PagingDataAdapter<ArtItem, ArtListAdapter.ListItemViewHolder>(ArtItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) {
            getItem(it)?.objectNumber?.let { objectNr ->
                clickListener(objectNr)
            }
        }
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ListItemViewHolder(
        private val binding: ListItemBinding,
        private val clickAtPosition: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener { clickAtPosition(bindingAdapterPosition) }
        }

        fun bind(item: ArtItem) {
            binding.apply {
                Glide.with(artPhoto.context)
                    .load(item.webImage.url)
                    .placeholder(R.color.grey_900)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(artPhoto)
                artTitle.text = item.title
            }
        }
    }
}

private class ArtItemDiffCallback : DiffUtil.ItemCallback<ArtItem>() {
    override fun areItemsTheSame(oldItem: ArtItem, newItem: ArtItem): Boolean {
        return oldItem.objectNumber == newItem.objectNumber
    }

    override fun areContentsTheSame(oldItem: ArtItem, newItem: ArtItem): Boolean {
        return oldItem == newItem
    }
}
