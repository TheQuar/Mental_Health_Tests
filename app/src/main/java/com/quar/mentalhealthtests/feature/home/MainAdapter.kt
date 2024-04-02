package com.quar.mentalhealthtests.feature.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quar.mentalhealthtests.data.room.table.Category
import com.quar.mentalhealthtests.databinding.RvMainItemBinding

class MainAdapter(private var categoryList: List<Category>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var onItemClickListener: ((Int, String) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int, String) -> Unit) {
        onItemClickListener = listener
    }

    inner class ViewHolder(val binding: RvMainItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = categoryList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            tvName.text = categoryList.get(position).name
        }

        holder.binding.root.setOnClickListener {
            val item = categoryList.get(position)
            onItemClickListener?.invoke(item.id, item.name)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<Category>) {
        categoryList = data
        notifyDataSetChanged()
    }
}