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
    private lateinit var shareF : SharedPreferences
    private var holderList : MutableList<String> = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        confB = ActCreateBinding.inflate(layoutInflater)
        setContentView(confB.root)


        enableEdgeToEdge()
        initShare()
        holderList.add(readerList().replace(Regex("[\\[\\]]"),""))

        start()
    }

    fun start(){
        confB.botonCrear.setOnClickListener {
            addUserList()
            confB.crearUser.setText("")
            confB.crearCon.setText("")
            confB.repiteCon.setText("")
        }
        confB.botonBorrar.setOnClickListener {
            borrarLista()
        }
    }

    fun addUserList(){
        var name = confB.crearUser.text.toString()
        var pass1 = confB.crearCon.text.toString()
        var pass2 = confB.repiteCon.text.toString()
        if(name.isNotEmpty() && pass1.isNotEmpty()){
        if (pass1.equals(pass2)){
            //cleaning()
            holderList.add(User(name,pass1).toString())

            writeThings(holderList.toString())

            Log.d("--Lista que holdea el holder--", holderList.toString())


        }else{
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
        }}else{
            Toast.makeText(this, "Que haces?", Toast.LENGTH_SHORT).show()
        }
    }

    fun cleaning(){
        var name = getString(R.string.sharePref);
        getSharedPreferences(name, Context.MODE_PRIVATE).edit().remove(getString(R.string.userList)).apply()
    }

    fun initShare(){
        var name = getString(R.string.sharePref)
        this.shareF = getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun writeThings(valorS : String){
        var name = getString(R.string.sharePref)

        //El hecho de añadir una lista a esats preferencias, me va a obligar a hacer piruetas para sacar el usuario de uno en uno si lo meto como string
        getSharedPreferences(name, Context.MODE_PRIVATE).edit().putString(getString(R.string.userList), valorS).apply()
        Toast.makeText(this, "Añadido el usuario", Toast.LENGTH_LONG).show()
    }

    fun readerList(): String {
        var userP = shareF.getString(getString(R.string.userList),"vacio")
        Log.d("---Lista la que está---", userP.toString())
        return userP.toString()
    }

    fun borrarLista(){
        holderList.clear()
        cleaning()
        readerList()
        Toast.makeText(this,"Usuarios eliminados", Toast.LENGTH_LONG).show()
    }
}