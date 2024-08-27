package com.example.icm.taller1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject
import kotlin.random.Random

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)

        val textName = findViewById<TextView>(R.id.textName4)
        val textCountry = findViewById<TextView>(R.id.textCountry4)
        val textCategory = findViewById<TextView>(R.id.textCategory4)
        val textPlan = findViewById<TextView>(R.id.textPlan4)
        val textPrice = findViewById<TextView>(R.id.textPrice4)

        var categoryMostFrequent = getMostFrequentCategory(MainActivity.favoriteListJson)
        val jsonObject = getRandomJsonObject(MainActivity.favoriteListJson, categoryMostFrequent.toString())

        textName.text = jsonObject?.getString("nombre")
        textCountry.text = jsonObject?.getString("pais")
        textCategory.text = jsonObject?.getString("categoria")
        textPlan.text = jsonObject?.getString("plan")
        textPrice.text = jsonObject?.getString("precio")

    }

    fun getMostFrequentCategory(jsonArrayList: ArrayList<JSONObject>): String? {
        // Crear un mapa para contar las frecuencias de cada categoría
        val categoryCountMap = mutableMapOf<String, Int>()

        // Recorrer el ArrayList
        for (jsonObject in jsonArrayList) {
            val category = jsonObject.optString("categoria", "")

            // Incrementar el contador de la categoría en el mapa
            if (category.isNotEmpty()) {
                val currentCount = categoryCountMap.getOrDefault(category, 0)
                categoryCountMap[category] = currentCount + 1
            }
        }

        // Encontrar la categoría con la mayor frecuencia
        return categoryCountMap.maxByOrNull { it.value }?.key
    }

    fun getRandomJsonObject(jsonArray: ArrayList<JSONObject>, category: String): JSONObject? {
        val randomIndex = Random.nextInt(jsonArray.size)
        if(jsonArray[randomIndex].getString("category") == category) {
            return jsonArray[randomIndex]
        }
        else {
            return getRandomJsonObject(jsonArray, category)
        }
    }
}