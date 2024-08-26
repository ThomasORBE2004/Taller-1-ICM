package com.example.icm.taller1

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main5)

        val boton = findViewById<Button>(R.id.buttonFav)

        boton.setOnClickListener {
            Toast.makeText(this, "Añadido a Favoritos", Toast.LENGTH_SHORT).show()
            boton.isEnabled = false
        }
    }
}