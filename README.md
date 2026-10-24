# NexFood

Modern, tamamen çevrimdışı çalışan bir yemek menüsü / sipariş uygulaması.
Native **Kotlin + Material 3** ile yazılmıştır.

## Özellikler

- 🍽️ 16 ürünlük yerel menü (kahvaltı, ana yemek, pizza, burger, tatlı, içecek)
- 🔎 Anlık arama ve kategori filtreleri
- 📄 Ürün detay sayfası (açıklama, içindekiler, puan, süre, kalori)
- 🛒 Adet kontrollü sepet, toplam tutar hesabı
- 💾 Sepet `SharedPreferences` ile kalıcı (uygulama kapanınca kaybolmaz)
- ✅ Sipariş onay akışı
- 🌙 Material 3 DayNight teması, edge-to-edge desteği

## Gereksinimler

- JDK 17+
- Android SDK 35 (build-tools 35)
- Gradle 8.14 (wrapper ile otomatik indirilir)

## Derleme

```bash
export ANDROID_HOME="$HOME/android-sdk"     # kendi SDK yolunuz
./gradlew assembleDebug                      # app/build/outputs/apk/debug/app-debug.apk
./gradlew assembleRelease                    # imzasız/debug imzalı release APK
```

`local.properties` dosyasına SDK yolunu yazın:

```properties
sdk.dir=/data/data/com.termux/files/home/android-sdk
```

## Kurulum

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## Paket yapısı

```
app/src/main/kotlin/com/nexfood/app/
├── MainActivity.kt        # Menü, arama, kategoriler
├── DetailActivity.kt      # Ürün detayı
├── CartActivity.kt        # Sepet ve sipariş
├── data/                  # Food, FoodRepository, Cart
├── ui/                    # Adapter'lar ve tema yardımcıları
└── util/                  # Para birimi biçimlendirme
```
