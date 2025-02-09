package br.edu.ifsp.dmo.trabalhofinal.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.dmo.trabalhofinal.R
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EUF
import br.edu.ifsp.dmo.trabalhofinal.data.enums.EUserType
import br.edu.ifsp.dmo.trabalhofinal.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonBack.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        binding.buttonRegister.setOnClickListener {
            val checked = binding.radioGroup.checkedRadioButtonId
            var tipo : EUserType? = null
            if(checked!= -1){
                val selected = findViewById<RadioButton>(checked)
                tipo = if(selected.text.toString()=="Fornecedor"){
                    EUserType.SUPPLIER
                }else{
                    EUserType.CLIENT
                }
            }

            val name = binding.textName.text.toString()
            val street = binding.textStreet.text.toString()
            val district = binding.textDistrict.text.toString()
            val city = binding.textCity.text.toString()
            val email = binding.textEmail.text.toString()
            val password = binding.textPassword.text.toString()

            val state = listOf(EUF.entries.toTypedArray())[binding.spinnerStates.selectedItemPosition]

            val mIntent = Intent()
            mIntent.putExtra("type",tipo)
            mIntent.putExtra("name",name)
            mIntent.putExtra("street",street)
            mIntent.putExtra("district",district)
            mIntent.putExtra("city",city)
            mIntent.putExtra("email",email)
            mIntent.putExtra("password",password)
            mIntent.putExtra("state",state)
            setResult(RESULT_OK,mIntent)
            finish()
        }
    }

    private fun setupSpinner() {
        val states = listOf(EUF.entries.toTypedArray().toString())
        val spinner = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            states
        )
        binding.spinnerStates.adapter = spinner
    }
}