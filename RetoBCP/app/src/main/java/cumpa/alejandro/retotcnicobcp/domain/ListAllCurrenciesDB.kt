package cumpa.alejandro.retotcnicobcp.domain

import cumpa.alejandro.retotcnicobcp.data.CurrencyRepository
import cumpa.alejandro.retotcnicobcp.domain.model.Currency
import javax.inject.Inject

class ListAllCurrenciesDB @Inject constructor(private val repository: CurrencyRepository) {
    suspend operator fun invoke() : List<Currency>{
        return repository.getAllCurrenciesFromDatabase()
    }
}