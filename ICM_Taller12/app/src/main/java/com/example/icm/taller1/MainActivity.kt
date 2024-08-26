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
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.onItemSelectedListener = this

        val buttonStart1 = findViewById<Button>(R.id.buttonStart1)
        val buttonStart2 = findViewById<Button>(R.id.buttonStart2)
        val buttonStart3 = findViewById<Button>(R.id.buttonStart3)

        buttonStart1.setOnClickListener{
            val intent = Intent (this, MainActivity2::class.java)
            intent.putExtra("filter", spinner.selectedItem.toString())
            startActivity(intent)
        }

        buttonStart2.setOnClickListener{
            //something else
        }

        buttonStart3.setOnClickListener{
            //something else
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val filter = parent.selectedItem

    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }


}