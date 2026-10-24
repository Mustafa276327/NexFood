package com.nexfood.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.nexfood.app.R
import com.nexfood.app.data.CartLine
import com.nexfood.app.util.Money

class CartAdapter(
    initialLines: List<CartLine>,
    private val onPlus: (CartLine) -> Unit,
    private val onMinus: (CartLine) -> Unit,
    private val onRemove: (CartLine) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val lines = mutableListOf<CartLine>().apply { addAll(initialLines) }

    fun submit(newLines: List<CartLine>) {
        lines.clear()
        lines.addAll(newLines)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int = lines.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(lines[position])
    }

    inner class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val emoji: TextView = view.findViewById(R.id.tvEmoji)
        private val name: TextView = view.findViewById(R.id.tvName)
        private val unitPrice: TextView = view.findViewById(R.id.tvUnitPrice)
        private val quantity: TextView = view.findViewById(R.id.tvQty)
        private val lineTotal: TextView = view.findViewById(R.id.tvLineTotal)
        private val plus: MaterialButton = view.findViewById(R.id.btnPlus)
        private val minus: MaterialButton = view.findViewById(R.id.btnMinus)
        private val remove: ImageButton = view.findViewById(R.id.btnRemove)

        fun bind(line: CartLine) {
            emoji.text = line.food.emoji
            name.text = line.food.name
            unitPrice.text = "${Money.format(line.food.price)} / adet"
            quantity.text = line.quantity.toString()
            lineTotal.text = Money.format(line.lineTotal)

            plus.setOnClickListener { onPlus(line) }
            minus.setOnClickListener { onMinus(line) }
            remove.setOnClickListener { onRemove(line) }
        }
    }
}
