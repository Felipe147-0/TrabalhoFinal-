package br.edu.ifsp.dmo.trabalhofinal.ui.choose

import android.os.Bundle
import android.widget.Spinner

import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.trabalhofinal.R
import br.edu.ifsp.dmo.trabalhofinal.databinding.ActivityChooseBinding

class ChooseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val SizeSpinner = binding.chooserSpinnerSize

        //continuacao

    }
}