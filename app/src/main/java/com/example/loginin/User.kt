package com.example.loginin

class User {
    private lateinit var nombre : String

    private lateinit var pass : String

    constructor(nombre: String?, pass: String) {
        this.nombre = nombre!!
        this.pass = pass
    }

    override fun toString(): String {
        return "nombre='$nombre', pass='$pass'"
    }


}