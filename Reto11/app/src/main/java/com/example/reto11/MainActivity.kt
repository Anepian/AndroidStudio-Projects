package com.example.reto11

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    private lateinit var etFecha: EditText
    private lateinit var btnGrabar: Button
    private lateinit var btnRecuperar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFecha = findViewById(R.id.etFecha)
        btnGrabar = findViewById(R.id.btnGrabar)
        btnRecuperar = findViewById(R.id.btnRecuperar)

        btnGrabar.setOnClickListener {
            grabarActividad()
        }

        btnRecuperar.setOnClickListener {
            recuperarActividad()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun grabarActividad() {
        // Obtenemos la fecha ingresada
        val fecha = etFecha.text.toString()

        // Creamos un archivo de texto con la fecha ingresada
        val archivo = File(applicationContext.filesDir, fecha)

        // Abrimos el archivo para escritura
        val fos = FileOutputStream(archivo)

        // Escribimos la actividad en el archivo
        fos.write("Actividad: ${SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())}\n".toByteArray())

        // Cerramos el archivo
        fos.close()

        // Mostramos un mensaje de confirmación
        Toast.makeText(this, "Actividad grabada correctamente", Toast.LENGTH_SHORT).show()
    }

    private fun recuperarActividad() {
        // Obtenemos la fecha ingresada
        val fecha = etFecha.text.toString()

        // Verificamos si existe un archivo con la fecha ingresada
        val archivo = File(applicationContext.filesDir, fecha)
        if (!archivo.exists()) {
            // Mostramos un mensaje de error
            Toast.makeText(this, "No existe actividad para la fecha ingresada", Toast.LENGTH_SHORT).show()
            return
        }

        // Abrimos el archivo para lectura
        val fis = FileInputStream(archivo)

        // Leemos el contenido del archivo
        val contenido = fis.readBytes().toString(Charsets.UTF_8)

        // Mostramos el contenido del archivo
        Toast.makeText(this, contenido, Toast.LENGTH_SHORT).show()
    }
}
