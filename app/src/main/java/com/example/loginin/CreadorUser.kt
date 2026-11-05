package com.example.loginin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.loginin.databinding.ActCreateBinding

class CreadorUser : AppCompatActivity(){
    private lateinit var confB : ActCreateBinding

    private lateinit var user: User
    private lateinit var shareF : SharedPreferences

    private var array: MutableList<User> = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        confB = ActCreateBinding.inflate(layoutInflater)
        setContentView(confB.root)


        enableEdgeToEdge()
        initShare()
        start()
    }

    fun start(){
        confB.botonCrear.setOnClickListener {
            addUserList()
        }
    }

    fun addUserList(){
        var name = confB.crearUser.text.toString()
        var pass1 = confB.crearCon.text.toString()
        var pass2 = confB.repiteCon.text.toString()
        if(name.isNotEmpty() && pass1.isNotEmpty()){
        if (pass1.equals(pass2)){
            array.add(User(name,pass1))
            var name = getString(R.string.sharePref)

            //El hecho de añadir una lista a esats preferencias, me va a obligar a hacer piruetas para sacar el usuario de uno en uno si lo meto como string
            getSharedPreferences(name, Context.MODE_PRIVATE).edit().putString(getString(R.string.userList), array.toString()).apply()
            Toast.makeText(this, "Añadido el usuario", Toast.LENGTH_LONG).show()

            listaCapturer()

            var li = shareF.getString(getString(R.string.userList),"vacio")

            Log.d("---ListaFich---", li.toString())
            Log.d("---Lista---", array.toString())
        }else{
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
        }}else{
            Toast.makeText(this, "Que haces?", Toast.LENGTH_SHORT).show()
        }
    }

    fun initShare(){
        var name = getString(R.string.sharePref)
        this.shareF = getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun listaCapturer(){
        var userP = shareF.getString(getString(R.string.userList),"vacio")

        var arrayDeDatos = userP.toString().split(",")
        var i = false
        for (x in arrayDeDatos){
            if(!i) {
                Log.d("---UsuarioLista---", x)
                i = true
            }else{
                Log.d("---passList---", x)
                i = false
            }
        }

        Log.d("---UsuarioLista---", arrayDeDatos.toString())
    }

}