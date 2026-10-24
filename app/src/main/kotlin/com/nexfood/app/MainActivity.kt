package com.nexfood.app

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.nexfood.app.data.Cart
import com.nexfood.app.data.Food
import com.nexfood.app.data.FoodRepository
import com.nexfood.app.ui.CategoryAdapter
import com.nexfood.app.ui.FoodAdapter
import com.nexfood.app.ui.applySystemBarsInsets
import com.nexfood.app.util.Money

class MainActivity : AppCompatActivity() {

    private lateinit var foodAdapter: FoodAdapter
    private var selectedCategory = "Tümü"
    private var query = ""

    private lateinit var emptyView: TextView
    private lateinit var listView: RecyclerView
    private lateinit var cartSummary: TextView
    private lateinit var headerCart: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Cart.load(this)
        applySystemBarsInsets(findViewById(R.id.root))

        emptyView = findViewById(R.id.tvEmpty)
        listView = findViewById(R.id.rvFoods)
        cartSummary = findViewById(R.id.tvCartSummary)
        headerCart = findViewById(R.id.btnHeaderCart)

        val categoriesView = findViewById<RecyclerView>(R.id.rvCategories)
        categoriesView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        categoriesView.adapter = CategoryAdapter(FoodRepository.categories) { category ->
            selectedCategory = category
            refresh()
        }

        foodAdapter = FoodAdapter(
            initialItems = emptyList(),
            onOpen = { food -> startActivity(DetailActivity.intent(this, food.id)) },
            onAdd = { food -> addToCart(food) }
        )
        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = foodAdapter

        findViewById<EditText>(R.id.etSearch).doAfterTextChanged { text ->
            query = text?.toString().orEmpty()
            refresh()
        }

        headerCart.setOnClickListener { openCart() }
        findViewById<MaterialButton>(R.id.btnGoCart).setOnClickListener { openCart() }

        refresh()
    }

    override fun onResume() {
        super.onResume()
        updateCartUi()
    }

    private fun addToCart(food: Food) {
        Cart.add(this, food.id)
        updateCartUi()
        Snackbar.make(findViewById(R.id.root), getString(R.string.added_to_cart), Snackbar.LENGTH_SHORT)
            .setAnchorView(findViewById(R.id.bottomBar))
            .show()
    }

    private fun openCart() {
        startActivity(Intent(this, CartActivity::class.java))
    }

    private fun refresh() {
        val items = FoodRepository.filter(selectedCategory, query)
        foodAdapter.submit(items)
        emptyView.isVisible = items.isEmpty()
        listView.isVisible = items.isNotEmpty()
        updateCartUi()
    }

    private fun updateCartUi() {
        val count = Cart.itemCount()
        headerCart.text = getString(R.string.cart_header, count)
        cartSummary.text = getString(R.string.cart_items, count, Money.format(Cart.total()))
    }
}
