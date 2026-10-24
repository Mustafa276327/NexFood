package com.nexfood.app.ui

import android.app.Activity
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Android 15 ile gelen zorunlu edge-to-edge davranışında içeriğin
 * durum çubuğu ve navigasyon çubuğu altında kalmamasını sağlar.
 */
fun Activity.applySystemBarsInsets(root: View) {
    ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
        val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
        insets
    }
}
