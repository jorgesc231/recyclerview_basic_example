package com.example.recyclerviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.RecyclerViewExampleApplication.Companion.wordList
import com.example.recyclerviewexample.databinding.ListItemBinding


class ListAdapter (private var list : List<String> = emptyList(), private val onItemSelected : (item_index : Int) -> Unit) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    class ListViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)

        fun bind(item_index : Int, onItemSelected : (Int) -> Unit) {
            binding.tvText.text = wordList[item_index]

            binding.root.setOnClickListener {
                onItemSelected(item_index)
            }
        }
    }

    fun updateList(list : List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(position, onItemSelected)
    }
}