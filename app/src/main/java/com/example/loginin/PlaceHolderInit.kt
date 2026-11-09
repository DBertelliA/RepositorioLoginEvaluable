package com.example.loginin

import android.os.Bundle
import android.view.ActionMode
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.loginin.databinding.ActInicioBinding

class PlaceHolderInit : AppCompatActivity() {
    private lateinit var imagen : ActInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imagen = ActInicioBinding.inflate(layoutInflater)
        setContentView(imagen.root)
        enableEdgeToEdge()
    }
}