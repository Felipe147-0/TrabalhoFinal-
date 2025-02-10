package br.edu.ifsp.dmo.trabalhofinal.ui.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.trabalhofinal.databinding.ActivityInfoBinding
import br.edu.ifsp.dmo.trabalhofinal.ui.main.MainActivity

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val txtLink1 = binding.infoTxtLink1
        val txtlink2 = binding.infoTxtLink2
        val btnVoltar = binding.buttonBack

        txtLink1.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse("https://www.sementesflorestais.org/mapa-das-sementes.html"))
            startActivity(intent)
        }

        txtlink2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse("https://arborescer.com.br/"))
            startActivity(intent)
        }

        btnVoltar.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }



    }


}