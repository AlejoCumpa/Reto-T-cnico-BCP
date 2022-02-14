package cumpa.alejandro.retotcnicobcp.domain

import android.util.Log
import cumpa.alejandro.retotcnicobcp.data.CurrencyRepository
import cumpa.alejandro.retotcnicobcp.data.database.entities.toDatabase
import cumpa.alejandro.retotcnicobcp.domain.model.Currency
import javax.inject.Inject

class ListAllCurrencies @Inject constructor(private val repository: CurrencyRepository) {
    suspend operator fun invoke() {
        val currencies = repository.getAllCurrenciesFromApi()
        Log.d("Domain", "ListAllCurrencies: $currencies")
         if(currencies.isNotEmpty()){
            repository.clearCurrencies()
            repository.insertCurrencies(currencies.map { it.toDatabase() })
        }
    }
}