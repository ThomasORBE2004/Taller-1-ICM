package com.example.icm.taller1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    companion object {
        var favoriteListJson: ArrayList<JSONObject> = ArrayList()
        var favoriteList: ArrayList<String> = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val spinner= setupSpinner()
        setupButtons(spinner)

    }

    private fun setupSpinner():Spinner{
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.onItemSelectedListener = this
        return spinner
    }

    private fun setupButtons(spinner: Spinner){

        val buttonStart1 = findViewById<Button>(R.id.buttonStart1)
        val buttonStart2 = findViewById<Button>(R.id.buttonStart2)
        val buttonStart3 = findViewById<Button>(R.id.buttonStart3)

        buttonStart1.setOnClickListener{
            val intent = Intent (this, MainActivity2::class.java)
            intent.putExtra("filter", spinner.selectedItem.toString())
            startActivity(intent)
        }

        buttonStart2.setOnClickListener{
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        buttonStart3.setOnClickListener{
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val filter = parent.selectedItem

    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }


}