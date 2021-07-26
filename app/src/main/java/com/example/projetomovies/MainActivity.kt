package com.example.projetomovies

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        println(ehUmBomFilme("Vingadores:Endgame"))
        criarNumero()
    }

    private fun ehUmBomFilme(filme: String?): String {
        return when {
            filme.isNullOrBlank() -> "Erro, preciso de um nome pra avaliar"
            filme.length < 5 -> "Um nome tão curto não pode ser bom"
            else -> "É, talvez seja bom"
        }
    }

    private fun criarNumero() {
        val list = List(10) { it+1 }
        val mutableList =  mutableListOf<String>()

        for(item in list){
            if(item%2 == 0) mutableList.add(item.toString())
            else mutableList.add("-")
        }

        println(mutableList)
    }
}