package br.edu.ifsp.dmo.trabalhofinal.ui.logged

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EUserType
import br.edu.ifsp.dmo.trabalhofinal.data.model.User
import br.edu.ifsp.dmo.trabalhofinal.databinding.ActivityLoggedBinding
import br.edu.ifsp.dmo.trabalhofinal.ui.choose.ChooseActivity
import br.edu.ifsp.dmo.trabalhofinal.ui.plant.PlantActivity
import br.edu.ifsp.dmo.trabalhofinal.ui.stock.StockActivity

class LoggedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoggedBinding
    private lateinit var viewModel: LoggedViewModel
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoggedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoggedViewModel::class.java)
        openBundle()
        setupListeners()
        setupObservers()
        setupLaunchers()
    }

    private fun openBundle() {
        if (intent.extras != null) {
            val user = intent.getSerializableExtra("user") as User

            val name = user.name
            val title = binding.loggedTextTitle.text.toString()
            binding.loggedTextTitle.text = title + name

            val id = user.id
            binding.userId.text = id.toString()
            if (user.userType == EUserType.SUPPLIER) {
                binding.clientButtonRequest.visibility = View.GONE
                binding.supplierButtonRegister.visibility = View.VISIBLE
            }
        }
    }

    private fun setupListeners() {
        binding.buttonQuit.setOnClickListener {
            handleLogout()
        }

        binding.clientButtonRequest.setOnClickListener {
            handleRequest()
        }

        binding.supplierButtonRegister.setOnClickListener {
            handleRegister()
        }

        binding.supplierButtonStock.setOnClickListener {
            handleStock()
        }
    }

    private fun handleStock() {
        val mIntent = Intent(this,StockActivity::class.java)
        mIntent.putExtra("user_id",binding.userId.text.toString())
        startActivity(mIntent)
    }

    private fun handleRegister() {
        val mIntent = Intent(this,PlantActivity::class.java)
        resultLauncher.launch(mIntent)
    }

    private fun handleRequest() {
        val mIntent = Intent(this,ChooseActivity::class.java)
        mIntent.putExtra("user_id",binding.userId.text.toString())
        resultLauncher.launch(mIntent)
    }

    private fun handleLogout() {
        viewModel.logout()
    }

    private fun setupObservers() {
        viewModel.loggedOut.observe(this, Observer {
            Toast.makeText(this,"Logout realizado com sucesso.", Toast.LENGTH_SHORT).show()
            finish()
        })

        viewModel.inserted.observe(this, Observer {
            if(it){
                Toast.makeText(this,"Plantas cadastradas com sucesso.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupLaunchers() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                if(it.resultCode == RESULT_OK){
                    val plantId = it.data?.getLongExtra("PLANT_ID", 0L)
                    val quantity = it.data?.getIntExtra("PLANT_QUANTITY", 0)
                    val userId = binding.userId.text.toString()
                    viewModel.registerNewPlantUser(userId,plantId!!,quantity!!)
                }
            }
        )
    }
}