package cumpa.alejandro.retotcnicobcp.data.network

import cumpa.alejandro.retotcnicobcp.data.model.CurrencyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyService @Inject constructor(private val api:CurrencyAPIClient) {

    suspend fun getCurrencies(): List<CurrencyModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllCurrencies()
            response.body() ?: emptyList()
        }
    }
}