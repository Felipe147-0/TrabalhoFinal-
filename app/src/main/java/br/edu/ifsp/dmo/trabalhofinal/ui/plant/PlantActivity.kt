package br.edu.ifsp.dmo.trabalhofinal.ui.plant

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.trabalhofinal.R
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EPlantSize
import br.edu.ifsp.dmo.trabalhofinal.data.model.Plant
import br.edu.ifsp.dmo.trabalhofinal.databinding.ActivityPlantBinding
import br.edu.ifsp.dmo.trabalhofinal.ui.plant.PlantViewModel

typealias Size = EPlantSize

class PlantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlantBinding
    private lateinit var viewModel: PlantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PlantViewModel::class.java)
        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        val sizeAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Size.entries.map { it.name }
        )
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.plantSpinnerSize.adapter = sizeAdapter
    }

    private fun setupListeners() {
        binding.buttonSave.setOnClickListener {
            handleSave()
        }
        binding.buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun handleSave() {
        val name = binding.textNamePlant.text.toString().trim()
        val species = binding.textSpecies.text.toString().trim()
        val description = binding.textDescription.text.toString().trim()
        val size = Size.entries[binding.plantSpinnerSize.selectedItemPosition]
        val frutiferous = binding.plantSwitchFrutiferous.isChecked

        if (name.isEmpty() || species.isEmpty()) {
            Toast.makeText(this, "Nome e espécie são obrigatórios", Toast.LENGTH_SHORT).show()
        } else {
            val plant = Plant(
                species = species,
                name = name,
                size = size,
                frutiferous = frutiferous
            )
            viewModel.insertPlant(plant)
            Toast.makeText(this, "Planta cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
