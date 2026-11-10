package com.example.loginin

import android.util.Log

fun comprobar(name : String, pass : String, lista : MutableList<String>) : Boolean{
    var keyU = false
    var keyP = false;
    var posi = 0;
    Log.d("--Lista en la comprobacion--", lista.toString())



    for(i in 0..lista.size - 1){
        if(i %2 == 0){
            if(lista[i].trim() == name){
                keyU = true
                posi = i
                break;
            }
        }
    }

    if(lista[posi+1].trim() == pass){
        keyP = true
    }

    return keyU && keyP
}

