package com.example.icm.taller1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        var filter = intent.getStringExtra("filter")
        val filteredItems = ArrayList<JSONObject>()
        val list: ListView = findViewById(R.id.listViewDestinos)

        //listView
        val json = JSONObject(loadJSONFromAsset())
        val destinosJson = json.getJSONArray("destinos")

        for (i in 0 until destinosJson.length()) {
            val jsonObject = destinosJson.getJSONObject(i)
            val category = jsonObject.getString("categoria")
            if (category == filter) {
                filteredItems.add(jsonObject)
            }
        }

        val itemNames = filteredItems.map { it.getString("nombre") }

        // Configurar el ArrayAdapter con los nombres extraídos
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemNames)
        list.adapter = adapter

        list.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val nombre = parent.selectedItem
                var pais = ""
                var categoria = ""
                var plan = ""
                var precio = 0

                filteredItems.forEach { jsonObject ->
                    val name = jsonObject.getString("nombre")

                    if(name == nombre) {
                        pais = jsonObject.getString("pais")
                        categoria = jsonObject.getString("categoria")
                        plan = jsonObject.getString("plan")
                        precio = jsonObject.getInt("precio")
                    }
                }

                val bundle = Bundle()
                bundle.putString("nombre", nombre.toString())
                bundle.putString("pais", pais.toString())
                bundle.putString("categoria", categoria.toString())
                bundle.putString("plan", plan.toString())
                bundle.putInt("precio", precio.toInt())

                val intent = Intent (this@MainActivity2, MainActivity5::class.java)
                intent.putExtra("bundle", bundle)
                startActivity(intent)

            }
        })

    }

    fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val isStream: InputStream = assets.open("destinos.json")
            val size:Int = isStream.available()
            val buffer = ByteArray(size)
            isStream.read(buffer)
            isStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}