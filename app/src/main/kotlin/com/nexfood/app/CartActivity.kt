package com.nexfood.app

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nexfood.app.data.Cart
import com.nexfood.app.data.CartLine
import com.nexfood.app.ui.CartAdapter
import com.nexfood.app.ui.applySystemBarsInsets
import com.nexfood.app.util.Money

class CartActivity : AppCompatActivity() {

    private lateinit var adapter: CartAdapter
    private lateinit var emptyView: TextView
    private lateinit var listView: RecyclerView
    private lateinit var totalView: TextView
    private lateinit var checkoutButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        Cart.load(this)
        applySystemBarsInsets(findViewById(R.id.root))

        emptyView = findViewById(R.id.tvEmpty)
        listView = findViewById(R.id.rvCart)
        totalView = findViewById(R.id.tvTotal)
        checkoutButton = findViewById(R.id.btnCheckout)

        adapter = CartAdapter(
            initialLines = emptyList(),
            onPlus = { line -> updateQuantity(line, line.quantity + 1) },
            onMinus = { line -> updateQuantity(line, line.quantity - 1) },
            onRemove = { line ->
                Cart.remove(this, line.food.id)
                render()
            }
        )
        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = adapter

        findViewById<MaterialButton>(R.id.btnBack).setOnClickListener { finish() }
        checkoutButton.setOnClickListener { confirmOrder() }

        render()
    }

    private fun updateQuantity(line: CartLine, newQuantity: Int) {
        Cart.setQuantity(this, line.food.id, newQuantity)
        render()
    }

    private fun render() {
        val lines = Cart.lines()
        adapter.submit(lines)
        val isEmpty = lines.isEmpty()
        emptyView.isVisible = isEmpty
        listView.isVisible = !isEmpty
        totalView.text = Money.format(Cart.total())
        checkoutButton.isEnabled = !isEmpty
    }

    private fun confirmOrder() {
        val lines = Cart.lines()
        if (lines.isEmpty()) return

        val total = Money.format(Cart.total())
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.checkout_title)
            .setMessage(getString(R.string.checkout_message, total))
            .setNegativeButton(R.string.checkout_no, null)
            .setPositiveButton(R.string.checkout_yes) { _, _ -> completeOrder() }
            .show()
    }

    private fun completeOrder() {
        Cart.clear(this)
        render()

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.order_success_title)
            .setMessage(R.string.order_success_message)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { _, _ -> finish() }
            .show()
    }
}
