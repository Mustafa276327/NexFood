package com.nexfood.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.nexfood.app.R
import com.nexfood.app.data.Food
import com.nexfood.app.util.Money

class FoodAdapter(
    initialItems: List<Food>,
    private val onOpen: (Food) -> Unit,
    private val onAdd: (Food) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private val items = mutableListOf<Food>().apply { addAll(initialItems) }

    fun submit(newItems: List<Food>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val emoji: TextView = view.findViewById(R.id.tvEmoji)
        private val name: TextView = view.findViewById(R.id.tvName)
        private val description: TextView = view.findViewById(R.id.tvDescription)
        private val meta: TextView = view.findViewById(R.id.tvMeta)
        private val price: TextView = view.findViewById(R.id.tvPrice)
        private val addButton: MaterialButton = view.findViewById(R.id.btnAdd)

        fun bind(food: Food) {
            emoji.text = food.emoji
            name.text = food.name
            description.text = food.description
            meta.text = "⭐ ${food.rating}  ·  ⏱ ${food.minutes} dk"
            price.text = Money.format(food.price)

            itemView.setOnClickListener { onOpen(food) }
            addButton.setOnClickListener { onAdd(food) }
        }
    }
}
