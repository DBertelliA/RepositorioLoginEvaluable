package com.example.loginin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var confB : ActivityMainBinding

    private lateinit var listaUsers : MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        confB = ActivityMainBinding.inflate(layoutInflater)
        setContentView(confB.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var sharef : SharedPreferences = getSharedPreferences(getString(R.string.sharePref), Context.MODE_PRIVATE)
        listaUsers = sharef.getString(getString(R.string.userList),"")?.split(",")?.toMutableList() ?: mutableListOf()
        listaUsers = listaUsers.toString().replace(Regex("[\\[\\]]"),"")?.split(",")?.toMutableList() ?: mutableListOf()

        Log.d("--Lista obtenida--", listaUsers.toString())

        start()
    }

    fun start(){
        confB.logIn.setOnClickListener {
            var nombre = confB.usuarioIntro.text.toString()
            var contra = confB.contraIntro.text.toString()

            if((nombre.isNotEmpty() && contra.isNotEmpty()) && comprobar(nombre, contra, listaUsers)){
                var intencion = Intent(this, PlaceHolderInit::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }
                startActivity(intencion)
            }else{
                Toast.makeText(this, "Alguno de los campos es incorrecto", Toast.LENGTH_LONG).show()
            }

        }
        confB.crearInter.setOnClickListener {
            var intencion = Intent(this, CreadorUser::class.java).apply {
                putExtra("Back",false)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startActivity(intencion)
        }
        //Log.d("//Bolea", intent.getBooleanExtra("Back",true).toString())
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}