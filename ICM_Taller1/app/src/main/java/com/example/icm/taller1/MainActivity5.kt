package com.example.icm.taller1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main5)

        var bundle = intent.getBundleExtra("bundle")
        var name = bundle?.getString("nombre")
        var country = bundle?.getString("pais")
        var category = bundle?.getString("categoria")
        var plan = bundle?.getString("plan")
        var price = "USD " + bundle?.getString("precio")

        val textName = findViewById<TextView>(R.id.textName)
        val textCountry = findViewById<TextView>(R.id.textCountry)
        val textCategory = findViewById<TextView>(R.id.textCategory)
        val textPlan = findViewById<TextView>(R.id.textPlan)
        val textPrice = findViewById<TextView>(R.id.textPrice)

        textName.text = name
        textCountry.text = country
        textCategory.text = category
        textPlan.text = plan
        textPrice.text = price

        val jsonObject = JSONObject()

        jsonObject.put("nombre", name)
        jsonObject.put("pais", country)
        jsonObject.put("categoria", category)
        jsonObject.put("plan", plan)
        jsonObject.put("precio", price)

        val boton = findViewById<Button>(R.id.buttonFav)

        boton.setOnClickListener {
            Toast.makeText(this, "Añadido a Favoritos", Toast.LENGTH_SHORT).show()
            boton.isEnabled = false
            MainActivity.favoriteListJson.add(jsonObject)
            MainActivity.favoriteList.add(name.toString())
        }
    }
}