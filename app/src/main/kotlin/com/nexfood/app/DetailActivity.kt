package com.nexfood.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.nexfood.app.data.Cart
import com.nexfood.app.data.Food
import com.nexfood.app.data.FoodRepository
import com.nexfood.app.ui.applySystemBarsInsets
import com.nexfood.app.util.Money

class DetailActivity : AppCompatActivity() {

    private lateinit var food: Food
    private var quantity = 1

    private lateinit var quantityView: TextView
    private lateinit var addButton: MaterialButton
    private lateinit var minusButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val foodId = intent.getIntExtra(EXTRA_FOOD_ID, -1)
        val found = FoodRepository.findById(foodId)
        if (found == null) {
            finish()
            return
        }
        food = found

        Cart.load(this)
        applySystemBarsInsets(findViewById(R.id.root))

        quantityView = findViewById(R.id.tvQty)
        addButton = findViewById(R.id.btnAddToCart)
        minusButton = findViewById(R.id.btnMinus)

        findViewById<TextView>(R.id.tvEmoji).text = food.emoji
        findViewById<TextView>(R.id.tvName).text = food.name
        findViewById<TextView>(R.id.tvDescription).text = food.description
        findViewById<TextView>(R.id.tvMeta).text =
            "⭐ ${food.rating}   ·   ⏱ ${food.minutes} dk   ·   🔥 ${food.calories} kcal"
        findViewById<TextView>(R.id.tvIngredients).text = food.ingredients.joinToString("  ·  ")

        findViewById<MaterialButton>(R.id.btnBack).setOnClickListener { finish() }
        minusButton.setOnClickListener { changeQuantity(-1) }
        findViewById<MaterialButton>(R.id.btnPlus).setOnClickListener { changeQuantity(1) }
        addButton.setOnClickListener { addToCart() }

        updateQuantityUi()
    }

    private fun changeQuantity(delta: Int) {
        quantity = (quantity + delta).coerceIn(1, 20)
        updateQuantityUi()
    }

    private fun updateQuantityUi() {
        quantityView.text = quantity.toString()
        minusButton.isEnabled = quantity > 1
        addButton.text = getString(R.string.detail_add, Money.format(food.price * quantity))
    }

    private fun addToCart() {
        Cart.add(this, food.id, quantity)
        Snackbar.make(findViewById(R.id.root), getString(R.string.added_to_cart), Snackbar.LENGTH_SHORT)
            .setAnchorView(addButton)
            .setAction(getString(R.string.go_to_cart)) {
                startActivity(Intent(this, CartActivity::class.java))
            }
            .show()
    }

    companion object {
        private const val EXTRA_FOOD_ID = "extra_food_id"

        fun intent(context: Context, foodId: Int): Intent =
            Intent(context, DetailActivity::class.java).putExtra(EXTRA_FOOD_ID, foodId)
    }
}
