package com.example.icm.taller1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val filter = intent.getStringExtra("filter")
        val filteredItems = ArrayList<JSONObject>()
        val list: ListView = findViewById(R.id.listViewDestinos)

        // Cargar y filtrar datos
        val json = JSONObject(loadJSONFromAsset())
        val destinosJson = json.getJSONArray("destinos")

        for (i in 0 until destinosJson.length()) {
            val jsonObject = destinosJson.getJSONObject(i)
            val category = jsonObject.getString("categoria")
            if (category == filter || filter == "Todos") {
                filteredItems.add(jsonObject)
            }
        }

        val itemNames = filteredItems.map { it.getString("nombre") }

        // Configurar el ArrayAdapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemNames)
        list.adapter = adapter

        // Configurar el listener para los ítems de la lista
        list.setOnItemClickListener { _, _, position, _ ->
            val nombre = list.getItemAtPosition(position).toString()
            var pais = ""
            var categoria = ""
            var plan = ""
            var precio = 0

            filteredItems.forEach { jsonObject ->
                val name = jsonObject.getString("nombre")

                if (name == nombre) {
                    pais = jsonObject.getString("pais")
                    categoria = jsonObject.getString("categoria")
                    plan = jsonObject.getString("plan")
                    precio = jsonObject.getInt("precio")
                }
            }

            val bundle = Bundle().apply {
                putString("nombre", nombre)
                putString("pais", pais)
                putString("categoria", categoria)
                putString("plan", plan)
                putString("precio", precio.toString())
            }

            val intent = Intent(this@MainActivity2, MainActivity5::class.java).apply {
                putExtra("bundle", bundle)
            }
            startActivity(intent)
        }
    }

    private fun cargarJson(){

    }

    private fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("destinos.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json
    }
}
