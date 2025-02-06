package com.example.bostatask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bostatask.databinding.ActivityMainBinding
import com.example.bostatask.view.CityAdapter
import com.example.bostatask.viewmodel.CitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: CitiesViewModel by viewModels()
    private lateinit var adapter: CityAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeData()
        viewModel.fetchCities()

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                filterCities(query)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
    private fun filterCities(query: String) {

        val originalCities = viewModel.citiesState.value?.getOrNull()?.data ?: return

        val filteredCities = if (query.isNullOrBlank()) {
            originalCities
        } else {
            originalCities.filter { city ->
                city.cityName.contains(query, ignoreCase = true)
            }
        }

        adapter.updateData(filteredCities)
    }

    private fun setupRecyclerView() {
        adapter = CityAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.citiesState.collect { result ->
                result?.onSuccess { response ->
                    binding.progressBar.visibility= View.GONE
                    binding.recyclerView.visibility= View.VISIBLE
                    adapter.updateData(response.data)
                }
            }
        }
    }
}
