package br.edu.ifsp.dmo.trabalhofinal.ui.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.trabalhofinal.R
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
        val txtlink3 = binding.infoTxtLink3
        val btnVoltar = binding.buttonBack

        txtLink1.setOnClickListener {
            val mintent = Intent(Intent.ACTION_VIEW,Uri.parse(getString(R.string.link1)))
            startActivity(mintent)
        }

        txtlink2.setOnClickListener {
            val mintent = Intent(Intent.ACTION_VIEW,Uri.parse(getString(R.string.link2)))
            startActivity(mintent)
        }

        txtlink3.setOnClickListener {
            val mintent = Intent(Intent.ACTION_VIEW,Uri.parse(getString(R.string.link3)))
            startActivity(mintent)
        }

        btnVoltar.setOnClickListener {
            finish()
        }



    }


}