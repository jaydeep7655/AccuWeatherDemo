package com.example.accuweatherdemo.presentation.searchlocation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.accuweatherdemo.databinding.ItemLocationBinding
import com.example.domain.model.Location
import com.example.domain.model.locationDiff
import java.util.*
import kotlin.collections.ArrayList

class LocationListAdapter(private val onItemClick: (Location) -> Unit) :
    ListAdapter<Location, LocationListAdapter.MealViewHolder>(locationDiff), Filterable {

    private var tempList = ArrayList<Location>()
    var unFilteredList = ArrayList<Location>()

    /*init {
        tempList = currentList as ArrayList<Location>
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding =
            ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MealViewHolder(binding)
        binding.root.setOnClickListener {
            onItemClick(getItem(holder.bindingAdapterPosition))
        }
        return holder
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MealViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Location?) {
            binding.run {
                this.item = item
                executePendingBindings()
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    tempList = unFilteredList
                } else {
                    val resultList = ArrayList<Location>()
                    for (row in unFilteredList) {
                        if (row.englishName!!.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    tempList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = tempList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.let {
                    tempList = results?.values as ArrayList<Location>
                    submitList(tempList)
                }

            }

        }
    }
}