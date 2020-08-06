package com.example.kotnamesduet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotnamesduet.databinding.CardItemBinding

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    val items = ArrayList<Char>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CardItemBinding.bind(view)

        fun bind(card: Char) {
            binding.bgCard.setImageResource(
                when (card) {
                    'A' -> R.drawable.card_black
                    'G' -> R.drawable.card_green
                    else -> R.drawable.card_neutral
                }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = items[position]
        holder.bind(card)
    }

}