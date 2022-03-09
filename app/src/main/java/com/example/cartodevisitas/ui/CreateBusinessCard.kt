package com.example.cartodevisitas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cartodevisitas.App
import com.example.cartodevisitas.R
import com.example.cartodevisitas.data.BusinessCard
import com.example.cartodevisitas.databinding.ActivityCreateBusinessCardBinding

class CreateBusinessCard : AppCompatActivity() {

    private val binding by lazy {
        ActivityCreateBusinessCardBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configCloseButton()

        configFinalizeButton()
    }

    private fun configFinalizeButton() {
        binding.btFinalize.setOnClickListener {
            with(binding) {
                val name = inputName.text.toString()
                val phone = inputPhone.text.toString()
                val email = inputEmail.text.toString()
                val companyName = inputCompanyName.text.toString()
                val color = inputColor.text.toString()

                val businessCard = BusinessCard(
                    name = name, phone = phone, email = email,
                    company_name = companyName, cardBackground = color
                )

                viewModel.repository.insert(businessCard)

                Toast.makeText(
                    this@CreateBusinessCard,
                    "Cartão salvo com sucesso", Toast.LENGTH_SHORT
                )
                    .show()

            }
                finish()
        }
    }

    private fun configCloseButton() {
        binding.btClose.setOnClickListener {
            finish()
        }
    }
}