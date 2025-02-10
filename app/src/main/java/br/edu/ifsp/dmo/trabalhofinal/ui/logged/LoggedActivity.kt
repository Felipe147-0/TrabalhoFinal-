package br.edu.ifsp.dmo.trabalhofinal.ui.logged

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EUserType
import br.edu.ifsp.dmo.trabalhofinal.data.model.User
import br.edu.ifsp.dmo.trabalhofinal.databinding.ActivityLoggedBinding

class LoggedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoggedBinding
    private lateinit var viewModel: LoggedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoggedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoggedViewModel::class.java)
        openBundle()
        setupListeners()
        setupObservers()
        setupLauncher()
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
                binding.supplierButtonStock.visibility = View.VISIBLE
                binding.supplierButtonRegister.visibility = View.VISIBLE
            }
        }
    }

    private fun setupListeners() {
        binding.buttonQuit.setOnClickListener {
            handleLogout()
        }
    }

    private fun handleLogout() {
        viewModel.logout()
    }

    private fun setupObservers() {
        viewModel.loggedOut.observe(this, Observer {
            Toast.makeText(this,"Logout realizado com sucesso.", Toast.LENGTH_SHORT).show()
            finish()
        })
    }

    private fun setupLauncher() {

    }
}