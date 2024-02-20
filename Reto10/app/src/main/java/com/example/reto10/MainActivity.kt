package com.example.reto10

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var txtNumero: EditText
    private lateinit var btnJugar: Button
    private lateinit var txtMensaje: TextView
    private var numeroAleatorio: Int = 0
    private var puntaje: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener los elementos de la interfaz
        txtNumero = findViewById(R.id.txtNumero)
        btnJugar = findViewById(R.id.btnJugar)
        txtMensaje = findViewById(R.id.txtMensaje)

        // Leer el puntaje del archivo de preferencias
        puntaje = obtenerPuntaje()

        // Generar un número aleatorio
        numeroAleatorio = Random().nextInt(50) + 1

        // Asignar un evento al botón
        btnJugar.setOnClickListener {
            // Obtener el número ingresado por el usuario
            val numeroIngresado = txtNumero.text.toString().toInt()

            // Comparar el número ingresado con el número aleatorio
            if (numeroIngresado == numeroAleatorio) {
                // El usuario ganó
                txtMensaje.text = "¡Ganaste! Tu puntaje es ${puntaje + 1}"
                puntaje++
            } else if (numeroIngresado < numeroAleatorio) {
                // El número ingresado es menor que el número aleatorio
                txtMensaje.text = "El número es mayor."
            } else {
                // El número ingresado es mayor que el número aleatorio
                txtMensaje.text = "El número es menor."
            }
        }
    }

    // Método para obtener el puntaje del archivo de preferencias
    private fun obtenerPuntaje(): Int {
        val preferencias = getSharedPreferences("puntaje", Context.MODE_PRIVATE)
        return preferencias.getInt("puntaje", 0)
    }

    // Método para guardar el puntaje en el archivo de preferencias
    private fun guardarPuntaje(puntaje: Int) {
        val preferencias = getSharedPreferences("puntaje", Context.MODE_PRIVATE)
        val editor = preferencias.edit()
        editor.putInt("puntaje", puntaje)
        editor.apply()
    }
}
