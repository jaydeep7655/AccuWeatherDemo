package com.example.domain.model

import androidx.recyclerview.widget.DiffUtil

data class Location(
    val englishName: String?,
    val key: String?
)

object locationDiff : DiffUtil.ItemCallback<Location>(){
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.key.equals(newItem.key)
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }

}



