package com.nexfood.app.data

/**
 * Uygulamanın yerel menü verisi. Ağ bağlantısı gerektirmez.
 */
object FoodRepository {

    val categories: List<String> = listOf(
        "Tümü", "Kahvaltı", "Ana Yemek", "Pizza", "Burger", "Tatlı", "İçecek"
    )

    val foods: List<Food> = listOf(
        Food(
            id = 1,
            name = "Serpme Kahvaltı",
            description = "Beyaz peynir, kaşar, zeytin, domates, salatalık, bal, tereyağı ve taze ekmek.",
            price = 185.0,
            category = "Kahvaltı",
            emoji = "🍳",
            minutes = 20,
            calories = 720,
            rating = 4.8,
            ingredients = listOf("Yumurta", "Peynir", "Zeytin", "Domates", "Bal", "Ekmek")
        ),
        Food(
            id = 2,
            name = "Menemen",
            description = "Tereyağında pişmiş yumurta, domates ve yeşil biber; sıcak ekmekle.",
            price = 95.0,
            category = "Kahvaltı",
            emoji = "🍅",
            minutes = 12,
            calories = 340,
            rating = 4.6,
            ingredients = listOf("Yumurta", "Domates", "Biber", "Tereyağı")
        ),
        Food(
            id = 3,
            name = "Avokado Tost",
            description = "Ekşi mayalı ekmek üzerinde avokado ezmesi, poşe yumurta ve çörek otu.",
            price = 120.0,
            category = "Kahvaltı",
            emoji = "🥑",
            minutes = 10,
            calories = 410,
            rating = 4.5,
            ingredients = listOf("Avokado", "Yumurta", "Ekşi mayalı ekmek", "Çörek otu")
        ),
        Food(
            id = 4,
            name = "Izgara Somon",
            description = "Fırında sebze yatağında ızgara somon fileto, limon ve dereotu sosu.",
            price = 240.0,
            category = "Ana Yemek",
            emoji = "🐟",
            minutes = 25,
            calories = 480,
            rating = 4.9,
            ingredients = listOf("Somon", "Kabak", "Havuç", "Limon", "Dereotu")
        ),
        Food(
            id = 5,
            name = "Tavuk Şiş",
            description = "Marine edilmiş tavuk göğsü, közlenmiş biber ve bulgur pilavı ile.",
            price = 175.0,
            category = "Ana Yemek",
            emoji = "🍗",
            minutes = 22,
            calories = 560,
            rating = 4.7,
            ingredients = listOf("Tavuk", "Biber", "Bulgur", "Soğan")
        ),
        Food(
            id = 6,
            name = "Kremalı Mantar Makarna",
            description = "Penne makarna, kremalı mantar sosu ve taze fesleğen.",
            price = 145.0,
            category = "Ana Yemek",
            emoji = "🍝",
            minutes = 18,
            calories = 620,
            rating = 4.6,
            ingredients = listOf("Penne", "Mantar", "Krema", "Fesleğen", "Parmesan")
        ),
        Food(
            id = 7,
            name = "Margarita Pizza",
            description = "İnce hamur, San Marzano domates sosu, taze mozzarella ve fesleğen.",
            price = 165.0,
            category = "Pizza",
            emoji = "🍕",
            minutes = 20,
            calories = 780,
            rating = 4.8,
            ingredients = listOf("Domates sosu", "Mozzarella", "Fesleğen", "Zeytinyağı")
        ),
        Food(
            id = 8,
            name = "Pepperoni Pizza",
            description = "Bol pepperoni, mozzarella ve acı biberli domates sosu.",
            price = 185.0,
            category = "Pizza",
            emoji = "🍕",
            minutes = 20,
            calories = 890,
            rating = 4.7,
            ingredients = listOf("Pepperoni", "Mozzarella", "Domates sosu", "Acı biber")
        ),
        Food(
            id = 9,
            name = "Dört Peynirli Pizza",
            description = "Mozzarella, gorgonzola, parmesan ve keçi peyniri dörtlüsü.",
            price = 195.0,
            category = "Pizza",
            emoji = "🧀",
            minutes = 22,
            calories = 850,
            rating = 4.6,
            ingredients = listOf("Mozzarella", "Gorgonzola", "Parmesan", "Keçi peyniri")
        ),
        Food(
            id = 10,
            name = "NexBurger",
            description = "180 gr dana köftesi, cheddar, karamelize soğan ve özel sos.",
            price = 210.0,
            category = "Burger",
            emoji = "🍔",
            minutes = 18,
            calories = 920,
            rating = 4.9,
            ingredients = listOf("Dana eti", "Cheddar", "Soğan", "Turşu", "Özel sos")
        ),
        Food(
            id = 11,
            name = "Crispy Chicken Burger",
            description = "Çıtır tavuk, coleslaw, ranch sos ve brioche ekmek.",
            price = 180.0,
            category = "Burger",
            emoji = "🍔",
            minutes = 16,
            calories = 810,
            rating = 4.5,
            ingredients = listOf("Tavuk", "Coleslaw", "Ranch sos", "Brioche ekmek")
        ),
        Food(
            id = 12,
            name = "Çikolatalı Sufle",
            description = "İçi akışkan sıcak çikolata sufle, vanilyalı dondurma ile.",
            price = 95.0,
            category = "Tatlı",
            emoji = "🍫",
            minutes = 15,
            calories = 520,
            rating = 4.9,
            ingredients = listOf("Bitter çikolata", "Yumurta", "Tereyağı", "Vanilyalı dondurma")
        ),
        Food(
            id = 13,
            name = "Cheesecake",
            description = "New York usulü fırın cheesecake, frambuazlı sos ile.",
            price = 105.0,
            category = "Tatlı",
            emoji = "🍰",
            minutes = 5,
            calories = 450,
            rating = 4.7,
            ingredients = listOf("Krem peynir", "Bisküvi", "Frambuaz", "Vanilya")
        ),
        Food(
            id = 14,
            name = "Baklava",
            description = "Antep fıstıklı, ince açılmış ev baklavası (4 dilim).",
            price = 130.0,
            category = "Tatlı",
            emoji = "🍯",
            minutes = 5,
            calories = 610,
            rating = 4.8,
            ingredients = listOf("Antep fıstığı", "Yufka", "Tereyağı", "Şerbet")
        ),
        Food(
            id = 15,
            name = "Limonata",
            description = "Taze sıkılmış limon, nane ve az şekerli ev yapımı limonata.",
            price = 55.0,
            category = "İçecek",
            emoji = "🍋",
            minutes = 3,
            calories = 120,
            rating = 4.6,
            ingredients = listOf("Limon", "Nane", "Şeker", "Su")
        ),
        Food(
            id = 16,
            name = "Filtre Kahve",
            description = "Tek origin çekirdeklerden demlenmiş sıcak filtre kahve.",
            price = 65.0,
            category = "İçecek",
            emoji = "☕",
            minutes = 4,
            calories = 5,
            rating = 4.8,
            ingredients = listOf("Kahve çekirdeği", "Su")
        )
    )

    fun findById(id: Int): Food? = foods.firstOrNull { it.id == id }

    fun filter(category: String, query: String): List<Food> {
        val normalized = query.trim().lowercase()
        return foods.filter { food ->
            val inCategory = category == "Tümü" || food.category == category
            val matchesQuery = normalized.isEmpty() ||
                food.name.lowercase().contains(normalized) ||
                food.description.lowercase().contains(normalized) ||
                food.category.lowercase().contains(normalized)
            inCategory && matchesQuery
        }
    }
}
