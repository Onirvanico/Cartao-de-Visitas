package com.example.cartodevisitas.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.cartodevisitas.App
import com.example.cartodevisitas.databinding.ActivityMainBinding
import com.example.cartodevisitas.ui.adapter.BusinessCardAdapter
import com.example.cartodevisitas.util.Image

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configFABAddCard()

        configRecycler()

        retrieveCards()

        adapter.listenerShare = { view ->
            Image.share(this, view)
        }
    }

    private fun retrieveCards() {
        viewModel.repository.getAll().observe(this, Observer { list ->
            adapter.submitList(list)
        })
    }

    private fun configRecycler() {
        binding.businessCardRecycler.setHasFixedSize(true)
        binding.businessCardRecycler.adapter = adapter
    }

    private fun configFABAddCard() {
        binding.fabAddCard.setOnClickListener {
            it.animate().rotationBy(360F).setDuration(500)
            val intent = Intent(this, CreateBusinessCard::class.java)
            startActivity(intent)
        }
    }
}