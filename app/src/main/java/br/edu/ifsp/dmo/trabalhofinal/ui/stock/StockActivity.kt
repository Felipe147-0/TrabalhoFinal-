package br.edu.ifsp.dmo.trabalhofinal.ui.stock

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.dmo.trabalhofinal.databinding.ActivityStockBinding
import br.edu.ifsp.dmo.trabalhofinal.ui.adapter.PlantUserAdapter

class StockActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStockBinding
    private lateinit var plantUserAdapter: PlantUserAdapter
    private val viewModel: StockViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObservers()
        loadStock()
    }

    private fun setupUI() {
        plantUserAdapter = PlantUserAdapter()
        binding.recyclerViewStock.apply {
            layoutManager = LinearLayoutManager(this@StockActivity)
            adapter = plantUserAdapter
        }
    }

    private fun setupObservers() {
        viewModel.plantUsers.observe(this) { (plantUsers, plantMap) ->
            plantUserAdapter.updatePlantMap(plantMap)
            plantUserAdapter.submitList(plantUsers)
        }
    }

    private fun loadStock() {
        val userId = intent.getLongExtra("user_id", -1)
        if (userId != -1L) {
            viewModel.loadStock(userId)
        } else {
            Toast.makeText(this, "Erro ao carregar estoque", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
