package br.edu.ifsp.dmo.trabalhofinal.ui.choose

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import br.edu.ifsp.dmo.trabalhofinal.data.enums.EPlantSize
import br.edu.ifsp.dmo.trabalhofinal.data.model.Plant
import br.edu.ifsp.dmo.trabalhofinal.databinding.ActivityChooseBinding
import br.edu.ifsp.dmo.trabalhofinal.databinding.DialogFilteredPlantsBinding
import br.edu.ifsp.dmo.trabalhofinal.databinding.DialogQuantityBinding
import br.edu.ifsp.dmo.trabalhofinal.ui.adapter.PlantAdapter
import br.edu.ifsp.dmo.trabalhofinal.ui.logged.LoggedActivity

class ChooseActivity : AppCompatActivity() {
    private lateinit var plantViewModel: ChooseViewModel
    private lateinit var binding: ActivityChooseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val dao = AppDatabase.getInstance(applicationContext).getPlantDao()
        plantViewModel = ViewModelProvider(this).get(ChooseViewModel::class.java)

        setupUI()
        setupListeners()
        observeViewModel()
    }

    private fun setupUI() {
        val SizeAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, EPlantSize.values().map { it.name }.toList()
        )
        SizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.chooserSpinnerSize.adapter = SizeAdapter

    }


    private fun setupListeners() {
        binding.buttonSearch.setOnClickListener {
            val isFrutiferous = binding.checkboxTypeFrutifera.isChecked
            val selectedSize =
                EPlantSize.valueOf(binding.chooserSpinnerSize.selectedItem.toString())

            // Chamando a ViewModel para buscar as plantas filtradas
            plantViewModel.FetchFilteredPlants(selectedSize, isFrutiferous)
        }

    }


    private fun showFilteredPlantsDialog(plants: List<Plant>) {
        val dialog = Dialog(this)

        val binding = DialogFilteredPlantsBinding.inflate(layoutInflater)

        dialog.setContentView(binding.root)

        val adapter = PlantAdapter(plants) { plant ->
            showQuantityDialog(plant)
            dialog.dismiss()
        }

        binding.recyclerViewFilteredPlants.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewFilteredPlants.adapter = adapter

        dialog.show()
    }


    private fun showQuantityDialog(plant: Plant) {
        val dialog = Dialog(this)
        val binding = DialogQuantityBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.dialogQTTitle.text = "Escolha a quantidade de mudas da planta ${plant.name}"

        binding.buttonQTConfirm.setOnClickListener {
            val quantity = binding.textQTQuantity.text.toString().toIntOrNull() ?: 0

            if (quantity > 0) {
                val intent = Intent(this, LoggedActivity::class.java).apply {
                    putExtra("PLANT_ID", plant.id)
                    putExtra("PLANT_QUANTITY", quantity)
                }
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Digite uma quantidade válida!", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        binding.buttonQTCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun observeViewModel() {
        plantViewModel.filteredPlants.observe(this, Observer { plants ->
            if (plants.isNotEmpty()) {
                showFilteredPlantsDialog(plants)
            }
        })
    }

}