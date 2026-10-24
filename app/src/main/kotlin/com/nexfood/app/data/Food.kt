package com.nexfood.app.data

/**
 * Menüdeki tek bir ürünü temsil eder.
 */
data class Food(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val emoji: String,
    val minutes: Int,
    val calories: Int,
    val rating: Double,
    val ingredients: List<String>
)
