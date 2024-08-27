package com.example.icm.taller1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        val list: ListView = findViewById(R.id.listViewFavorites)

        // Configurar el ArrayAdapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, MainActivity.favoriteList)
        list.adapter = adapter

    }
}