package com.alexander.vilca.lab05

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Ejercicio1 : AppCompatActivity() {

    private lateinit var greenView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio)

        greenView = findViewById(R.id.greenView)
        val buttonShow: Button = findViewById(R.id.buttonShow)
        val buttonHide: Button = findViewById(R.id.buttonHide)

        buttonShow.setOnClickListener {
            greenView.visibility = View.VISIBLE
        }

        buttonHide.setOnClickListener {
            greenView.visibility = View.GONE
        }
    }
}
