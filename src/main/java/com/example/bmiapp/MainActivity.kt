package com.example.bmiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculate = findViewById<Button>(R.id.btnCalcular)
        val txtPeso = findViewById<TextView>(R.id.txtPeso)
        val txtHeight = findViewById<TextView>(R.id.txtAltura)

        calculate.setOnClickListener{
            val peso1 = txtPeso.text
            val height1 = txtHeight.text
            val peso2 = peso1.toString().toFloat()
            val height2 = height1.toString().toFloat()


            if(peso2 in 1.00..440.00){
                if(height2 in 1.00..270.00){
                    val total = bmi(peso2,height2)
                    openActivity(total)
                }else{
                    Toast.makeText(this,"Altura debe estar entre 1 y 270 cm",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Peso debe estar entre 1 y 440 lbs",Toast.LENGTH_SHORT).show()
            }

        }

        calculate.setOnLongClickListener{
            Toast.makeText(this,"BMI: Peso(kg) ÷ Altura (m) ˆ 2", Toast.LENGTH_LONG).show()
            return@setOnLongClickListener true
        }


    }

    private fun openActivity(total: Float){
        when (total){
            in 0.00..19.00 -> startActivity(Intent(this,PesoInferior::class.java))
            in 19.01..24.99 -> startActivity(Intent(this,PesoIdeal::class.java))
            in 25.00..29.99 -> startActivity(Intent(this,Sobrepeso::class.java))
            else -> startActivity(Intent(this,Obesidad::class.java))
        }
    }


    private fun bmi(peso: Float, height: Float): Float {
        val kg = (peso / 2.204).toFloat()
        val m = (height/100.00).toFloat()
        return ((kg / (m * m)))
    }
}
