package cumpa.alejandro.retotcnicobcp.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import cumpa.alejandro.retotcnicobcp.databinding.ActivityCurrenciesBinding
import cumpa.alejandro.retotcnicobcp.di.CURRENCY
import cumpa.alejandro.retotcnicobcp.di.TYPE
import cumpa.alejandro.retotcnicobcp.domain.model.Currency
import cumpa.alejandro.retotcnicobcp.ui.adapter.CurrencyAdapter
import cumpa.alejandro.retotcnicobcp.ui.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrenciesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCurrenciesBinding

    private val countriesViewModel : CountriesViewModel by viewModels()
    private lateinit var sendGet: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCurrenciesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sendGet = intent.getStringExtra(TYPE)!!

        countriesViewModel.isLoading.observe(this, Observer {
            Log.d("Activity" , "isLoading: $it")
        })

        val listener = object: CurrencyAdapter.CurrenciesListener{
            override fun onClickItem(item: Currency) {
                val intent = Intent();
                intent.putExtra(TYPE, sendGet)
                intent.putExtra(CURRENCY, item);
                setResult(RESULT_OK, intent);
                finish();
            }
        }

        countriesViewModel.currenciesModel.observe(this, Observer {
            val adapter = CurrencyAdapter(listener)
            adapter.setCurrenciesList(it)
            binding.rvCurrencies.layoutManager = LinearLayoutManager(this.applicationContext)
            binding.rvCurrencies.adapter = adapter
            Log.d("Adapter" , "currencies: $it")
        })
        countriesViewModel.currenciesModel


        countriesViewModel.onCreate()

    }

}