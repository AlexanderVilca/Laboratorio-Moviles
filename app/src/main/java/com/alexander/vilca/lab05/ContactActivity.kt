package com.alexander.vilca.lab05

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexander.vilca.lab05.databinding.ActivityContactBinding
import com.google.android.material.snackbar.Snackbar

class ContactActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityContactBinding

    val PARAMETER_EXTRA_NOMBRE = "nombre"
    val PARAMETER_EXTRA_NUMERO = "numero"
    val PARAMETER_EXTRA_PRODUCTOS = "productos"
    val PARAMETER_EXTRA_DIRECCION = "direccion"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // Obtener los extras del intent
        val extras = intent.extras
        if (extras != null) {
            extras.getString(PARAMETER_EXTRA_NOMBRE)?.let {
                viewBinding.textNombreCliente.text = it
            }
            extras.getString(PARAMETER_EXTRA_NUMERO)?.let {
                viewBinding.textNumeroCliente.text = it
            }
            extras.getString(PARAMETER_EXTRA_PRODUCTOS)?.let {
                viewBinding.textProductos.text = it
            }
            extras.getString(PARAMETER_EXTRA_DIRECCION)?.let {
                viewBinding.textDireccion.text = it
            }
        }

        // Botón para realizar llamada
        viewBinding.btnLlamar.setOnClickListener {
            val telefono = viewBinding.textNumeroCliente.text.toString()
            if (validateInput(telefono)) {
                Call(telefono)
            }
        }

        // Botón para abrir WhatsApp con un mensaje personalizado
        viewBinding.btnWhatsApp.setOnClickListener {
            val telefono = viewBinding.textNumeroCliente.text.toString()
            val nombre = viewBinding.textNombreCliente.text.toString()
            val productos = viewBinding.textProductos.text.toString()
            val direccion = viewBinding.textDireccion.text.toString()

            if (validateInput(telefono, nombre, productos, direccion)) {
                WhatsApp(telefono, nombre, productos, direccion)
            }
        }

        // Botón para abrir Google Maps con la ubicación (dirección)
        viewBinding.btnMaps.setOnClickListener {
            val direccion = viewBinding.textDireccion.text.toString()
            if (validateInput(direccion)) {
                openMaps(direccion)
            }
        }
    }

    // Función para validar que los inputs no estén vacíos
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

    // Función para realizar una llamada
    private fun Call(telefono: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$telefono")
        startActivity(intent)
    }

    // Función para abrir WhatsApp con un mensaje personalizado
    private fun WhatsApp(telefono: String, nombre: String, productos: String, direccion: String) {
        try {
            val mensaje = "Hola $nombre, tus productos: $productos están en camino a la dirección: $direccion"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$telefono&text=${Uri.encode(mensaje)}")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Función para abrir Google Maps con la dirección del cliente
    private fun openMaps(direccion: String) {
        val uri = Uri.parse("geo:0,0?q=${Uri.encode(direccion)}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }
}
