package br.edu.ifsp.dmo.trabalhofinal.ui.choose

import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.dmo.trabalhofinal.R
import br.edu.ifsp.dmo.trabalhofinal.data.database.AppDatabase
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EPlantSize
import br.edu.ifsp.dmo.trabalhofinal.data.model.Plant
import br.edu.ifsp.dmo.trabalhofinal.data.repository.PlantRepository
import br.edu.ifsp.dmo.trabalhofinal.databinding.ActivityChooseBinding
import br.edu.ifsp.dmo.trabalhofinal.databinding.ActivityDialogFilteredPlantsBinding
import br.edu.ifsp.dmo.trabalhofinal.ui.choose.adapter.PlantAdapter
import br.edu.ifsp.dmo.trabalhofinal.ui.plant.PlantViewModel

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

    private fun setupUI(){
        val SizeAdapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,EPlantSize.values().map { it.name }.toList()
        )
        SizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.chooserSpinnerSize.adapter = SizeAdapter

    }


    private fun setupListeners(){
        binding.buttonSearch.setOnClickListener {
            val isFrutiferous = binding.checkboxTypeFrutifera.isChecked
            val selectedSize = EPlantSize.valueOf(binding.chooserSpinnerSize.selectedItem.toString())

            // Chamando a ViewModel para buscar as plantas filtradas
            plantViewModel.FetchFilteredPlants(selectedSize, isFrutiferous)
        }

    }


    private fun showFilteredPlantsDialog(plants: List<Plant>) {
        val dialog = Dialog(this)

        val binding = ActivityDialogFilteredPlantsBinding.inflate(layoutInflater)

        dialog.setContentView(binding.root)

        val adapter = PlantAdapter(plants){plant ->
            // fazer a logica de salvar os dados no intent aq
            dialog.dismiss()
        }

        binding.recyclerViewFilteredPlants.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewFilteredPlants.adapter = adapter

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