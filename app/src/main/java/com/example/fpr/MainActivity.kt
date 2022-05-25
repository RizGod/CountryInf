package com.example.fpr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.fpr.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            binding
            binding.searchButton.isClickable = false
            val countryName = binding.countryNameEditText.text.toString()
                .lowercase(Locale.getDefault())

            lifecycleScope.launch {
                try {
                    val countries = restCountriesApi.getCountryByName(countryName)
                    val country = countries[0]

                    binding.counrtyNameTextView.text = "${country.name} (${country.nativeName})"
                    binding.capitalTextView.text = country.capital
                    binding.populationTextView.text = formatNumber(country.population)
                    binding.areaTextView.text = formatNumber(country.area)
                    binding.languagesTextView.text = languagesToString(country.languages)
                    downloadFlag(this@MainActivity, country.flag, binding.imageView)
                    binding.resultLayout.visibility = View.VISIBLE
                    binding.statusLayout.visibility = View.INVISIBLE
                }catch (e: Exception) {
                    binding.statusTextView.text = "Страна не найдена"
                    binding.statusImageView.setImageResource(R.drawable.ic_baseline_error_outline_24)
                    binding.resultLayout.visibility = View.INVISIBLE
                    binding.statusLayout.visibility = View.VISIBLE
                }
            }
            binding.searchButton.isClickable = true
        }
    }
}
