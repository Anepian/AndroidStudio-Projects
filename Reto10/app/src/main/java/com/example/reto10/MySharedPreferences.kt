package com.example.reto10

class MySharedPreferences(context: Context, name: String, mode: Int) : SharedPreferences(context, name, mode) {

    override fun getInt(key: String, defaultValue: Int): Int {
        // Implementar tu propio código para obtener el valor
    }

    override fun putInt(key: String, value: Int) {
        // Implementar tu propio código para guardar el valor
    }

    val sharedPreferences = MySharedPreferences(this, "puntaje", Context.MODE_PRIVATE)

}
