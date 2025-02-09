package br.edu.ifsp.dmo.trabalhofinal.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.trabalhofinal.R
import br.edu.ifsp.dmo.trabalhofinal.data.model.User
import br.edu.ifsp.dmo.trabalhofinal.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.trabalhofinal.ui.info.InfoActivity
import br.edu.ifsp.dmo.trabalhofinal.ui.logged.LoggedActivity
import br.edu.ifsp.dmo.trabalhofinal.ui.register.RegisterActivity

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setupListeners()
        setupObservers()
        setupLauncher()
    }

    private fun setupLauncher() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {

            }
        )
    }

    private fun setupObservers() {
        viewModel.loggedIn.observe(this, Observer {
            if(it!=0L){
                fetchUserForLogged(it)
            }else{
                Toast.makeText(this,"Erro ao fazer login",Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.userLogged.observe(this, Observer {
            if(it!=null){
                navigateToLoggedActivity(it)
            }
        })
    }

    private fun navigateToLoggedActivity(user: User) {
        val mIntent = Intent(this, LoggedActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("user",user)
        mIntent.putExtras(bundle)
        startActivity(mIntent)
    }

    private fun fetchUserForLogged(id : Long) {
        if(!flag){
            flag = true
            viewModel.fetchUser(id)
        }
    }

    private fun setupListeners() {
        binding.buttonLogin.setOnClickListener {
            handleLogin()
        }

        binding.buttonInfo.setOnClickListener {
            moveToInfoActivity()
        }

        binding.buttonRegister.setOnClickListener {
            launchRegisterActivity()
        }
    }

    private fun launchRegisterActivity() {
        val mIntent = Intent(this,RegisterActivity::class.java)
        resultLauncher.launch(mIntent)
    }

    private fun moveToInfoActivity() {
        val mIntent = Intent(this, InfoActivity::class.java)
        startActivity(mIntent)
    }

    private fun handleLogin() {
        val email = binding.textEmail.text.toString()
        val senha = binding.textPassword.text.toString()
        val saveLogin = binding.checkboxSaveLogin.isChecked
        val stayLoggedIn = binding.checkboxStayLoggedin.isChecked

        binding.textEmail.setText("")
        binding.textPassword.setText("")
        binding.checkboxSaveLogin.isChecked = false
        binding.checkboxStayLoggedin.isChecked = false

        if(email.isNotEmpty()){
            viewModel.login(email,senha,saveLogin,stayLoggedIn)
        }else{
            Toast.makeText(this,"Preencha todos os dados.",Toast.LENGTH_SHORT).show()
        }
    }
}