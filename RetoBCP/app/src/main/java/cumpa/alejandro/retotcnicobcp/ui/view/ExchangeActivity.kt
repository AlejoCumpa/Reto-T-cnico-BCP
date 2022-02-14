package cumpa.alejandro.retotcnicobcp.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cumpa.alejandro.retotcnicobcp.databinding.ActivityExchangeBinding
import cumpa.alejandro.retotcnicobcp.di.CURRENCY
import cumpa.alejandro.retotcnicobcp.di.TYPE
import cumpa.alejandro.retotcnicobcp.domain.model.Currency
import cumpa.alejandro.retotcnicobcp.ui.viewmodel.ExchangeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExchangeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExchangeBinding
    private val exchangeViewModel : ExchangeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exchangeViewModel.sendCurrency.observe(this, Observer {
            binding.btnCurrencySend.text = it.currency_name
        })

        exchangeViewModel.getCurrency.observe(this, Observer {
            binding.btnCurrencyGet.text = it.currency_name
        })
        exchangeViewModel.sendAmount.observe(this, Observer {
            binding.etSend.setText(it.toString())
        })
        exchangeViewModel.getAmount.observe(this, Observer {
            binding.etGet.setText(it.toString())
            binding.tvBuysell.text ="Compra: ${it.toString()} | Venta: "
        })
        exchangeViewModel.buySellText.observe(this, Observer {
            binding.tvBuysell.text = it
        })



        binding.btnChangeCurrency.setOnClickListener {
            exchangeViewModel.changeCurrencies()
        }
        binding.etSend.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(binding.etSend.isFocused){
                    if (!s.isNullOrBlank()) {
                          exchangeViewModel.UpdateAmount(s.toString())
                    }
                    else{
                        binding.etSend.setText("0")
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnCurrencySend.setOnLongClickListener (View.OnLongClickListener {
            launchIntent("send")
            return@OnLongClickListener true
        })

        binding.btnCurrencyGet.setOnLongClickListener(View.OnLongClickListener {
            launchIntent(type = "get")
            return@OnLongClickListener true
        })
        exchangeViewModel.onCreate()
    }

    private fun launchIntent(type:String){
        val intent = Intent(this, CurrenciesActivity::class.java)
        intent.putExtra(TYPE, type)
        resultCurrencies.launch(intent)
    }

    private var resultCurrencies = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                when(it.getStringExtra(TYPE)){
                    "send" -> {
                        exchangeViewModel.UpdateSendCurrency( it.getSerializableExtra(CURRENCY) as Currency)
                    }
                    "get" -> {
                        exchangeViewModel.UpdateGetCurrency(it.getSerializableExtra(CURRENCY) as Currency)
                    }
                    else -> {
                    }
                }
                Log.d("Exchange Activity", "exchangeViewModel.getCurrency.value:$exchangeViewModel.getCurrency.value" )
                Log.d("Exchange Activity", "exchangeViewModel.sendCurrency.value:$exchangeViewModel.getCurrency.value" )
                exchangeViewModel.calculate()
            }
        }
    }
}