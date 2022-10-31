package com.example.practicashared

import android.R
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.practicashared.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadSpinner()
        initializeSharedPreference()
        loadData()
        binding.btnProcesar.setOnClickListener{
            //getValues()
            saveData()
            loadData()
        }
    }

    private fun getValues() {
        saveData()
    }

    private fun loadSpinner() {
        val lenguajes =
            arrayOf("Seleccione", "Programación Móvil II", "Programación Web III", "Game Development")
        val Slenguajes = binding.spinner
        Slenguajes.adapter =
            ArrayAdapter(applicationContext, R.layout.simple_spinner_dropdown_item, lenguajes)
    }


    private fun initializeSharedPreference() {
        sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    private fun saveData() {
        val spinner = binding.spinner
        val materia = spinner.selectedItem.toString()+""
        //binding.etNombre.setText(materia)
        val nombre = binding.etNombre.text.toString()+""
        val cal = binding.etCalMateria.text.toString()+""
        editor.apply{
            putString("nombre",nombre)
            putString("materia",materia)
            putString("calificacion",cal)
        }.apply()
    }


    private fun loadData() {
        val nombre = sharedPreferences.getString("nombre", "vacío")
        val materia = sharedPreferences.getString("materia", "vacío")
        val cal = sharedPreferences.getString("calificacion", "vacío")
        binding.txtMostrar.text = nombre + "\n" + materia +"\n"+ cal
    }
}