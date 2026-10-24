package com.nexfood.app.data

import android.content.Context

/**
 * Sepet verisi. Basitlik ve çevrimdışı çalışma için SharedPreferences'ta tutulur.
 */
object Cart {

    private const val PREFS = "nexfood_cart"
    private const val KEY_ITEMS = "items"

    private val quantities = linkedMapOf<Int, Int>()

    fun load(context: Context) {
        quantities.clear()
        val raw = context
            .getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(KEY_ITEMS, null)
            ?: return

        raw.split(",").forEach { entry ->
            val parts = entry.split(":")
            if (parts.size == 2) {
                val id = parts[0].toIntOrNull() ?: return@forEach
                val qty = parts[1].toIntOrNull() ?: return@forEach
                if (qty > 0 && FoodRepository.findById(id) != null) {
                    quantities[id] = qty
                }
            }
        }
    }

    private fun save(context: Context) {
        val raw = quantities.entries.joinToString(",") { "${it.key}:${it.value}" }
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_ITEMS, raw)
            .apply()
    }

    fun add(context: Context, foodId: Int, count: Int = 1) {
        if (count <= 0) return
        quantities[foodId] = (quantities[foodId] ?: 0) + count
        save(context)
    }

    fun setQuantity(context: Context, foodId: Int, quantity: Int) {
        if (quantity <= 0) {
            quantities.remove(foodId)
        } else {
            quantities[foodId] = quantity
        }
        save(context)
    }

    fun remove(context: Context, foodId: Int) {
        quantities.remove(foodId)
        save(context)
    }

    fun clear(context: Context) {
        quantities.clear()
        save(context)
    }

    fun quantityOf(foodId: Int): Int = quantities[foodId] ?: 0

    fun itemCount(): Int = quantities.values.sum()

    fun lines(): List<CartLine> = quantities.entries.mapNotNull { (id, qty) ->
        FoodRepository.findById(id)?.let { CartLine(it, qty) }
    }

    fun total(): Double = lines().sumOf { it.lineTotal }
}

data class CartLine(
    val food: Food,
    val quantity: Int
) {
    val lineTotal: Double get() = food.price * quantity
}
