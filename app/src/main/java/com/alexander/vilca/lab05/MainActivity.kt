package com.alexander.vilca.lab05

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexander.vilca.lab05.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    // Claves para los parámetros que se van a pasar a la segunda actividad
    val PARAMETER_EXTRA_NOMBRE = "nombre"
    val PARAMETER_EXTRA_NUMERO = "numero"
    val PARAMETER_EXTRA_PRODUCTOS = "productos"
    val PARAMETER_EXTRA_DIRECCION = "direccion"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // Acción del botón Registrar para enviar los datos a ContactActivity
        viewBinding.btnRegistrar.setOnClickListener {
            val nombreCliente = viewBinding.inputNombreCliente.text.toString()
            val numeroCliente = viewBinding.inputNumeroCliente.text.toString()
            val productos = viewBinding.inputProductos.text.toString()
            val direccion = viewBinding.inputDireccion.text.toString()

            if (validateInput(nombreCliente, numeroCliente, productos, direccion)) {
                val intent = Intent(this, ContactActivity::class.java).apply {
                    putExtra(PARAMETER_EXTRA_NOMBRE, nombreCliente)
                    putExtra(PARAMETER_EXTRA_NUMERO, numeroCliente)
                    putExtra(PARAMETER_EXTRA_PRODUCTOS, productos)
                    putExtra(PARAMETER_EXTRA_DIRECCION, direccion)
                }
                startActivity(intent)
            }
        }
    }

    // Función para validar que los campos no estén vacíos
    private fun validateInput(vararg inputs: String): Boolean {
        for (input in inputs) {
            if (input.isEmpty()) {
                // Mostrar un mensaje de error con Snackbar
                Snackbar.make(viewBinding.root, "Por favor, completa todos los campos.", Snackbar.LENGTH_LONG).show()
                return false
            }
        }
        return true
    }
}
