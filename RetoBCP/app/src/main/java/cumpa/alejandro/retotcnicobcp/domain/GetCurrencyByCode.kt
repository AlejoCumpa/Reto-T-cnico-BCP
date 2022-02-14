package cumpa.alejandro.retotcnicobcp.domain

import cumpa.alejandro.retotcnicobcp.data.CurrencyRepository
import cumpa.alejandro.retotcnicobcp.domain.model.Currency
import javax.inject.Inject

class GetCurrencyByCode @Inject constructor(
    private val repository: CurrencyRepository,
    ) {
    suspend operator fun invoke( currency_code: String) : Currency{
        return repository.getCurrencyByCode(currency_code = currency_code)
    }
}