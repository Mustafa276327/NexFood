package com.nexfood.app.util

import java.util.Locale

object Money {

    private val TR: Locale = Locale.forLanguageTag("tr-TR")

    fun format(value: Double): String = String.format(TR, "₺%.2f", value)
}
