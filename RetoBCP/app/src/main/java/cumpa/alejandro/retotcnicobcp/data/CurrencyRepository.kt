package cumpa.alejandro.retotcnicobcp.data

import android.util.Log
import cumpa.alejandro.retotcnicobcp.data.database.dao.CurrencyDao
import cumpa.alejandro.retotcnicobcp.data.database.entities.CurrencyEntity
import cumpa.alejandro.retotcnicobcp.data.model.CurrencyModel
import cumpa.alejandro.retotcnicobcp.data.network.CurrencyService
import cumpa.alejandro.retotcnicobcp.domain.model.Currency
import cumpa.alejandro.retotcnicobcp.domain.model.toDomain
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val api: CurrencyService,
    private val currencyDao: CurrencyDao
) {

    suspend fun getAllCurrenciesFromApi(): List<Currency> {
        val response: List<CurrencyModel> = api.getCurrencies()
        Log.d("Repository", "getAllCurrenciesFromApi:${response.toString()}")
        return response.map { it.toDomain() }
    }

    suspend fun getAllCurrenciesFromDatabase():List<Currency>{
        val response: List<CurrencyEntity> = currencyDao.getALlCurrencies()
        Log.d("Repository", "getAllCurrenciesFromDatabase:${response.toString()}")
        return response.map { it.toDomain() }
    }

    suspend fun getCurrencyByCode(currency_code: String):Currency{
        val response: CurrencyEntity = currencyDao.getCurrencyById(currency_code)

        Log.d("Repository", "currency_code:${currency_code}")
        Log.d("Repository", "getCurrencyByCode:${response.toString()}")
        return response.toDomain()
    }

    suspend fun insertCurrencies(quotes:List<CurrencyEntity>){
        currencyDao.insertAll(quotes)
    }

    suspend fun clearCurrencies(){
        currencyDao.deleteAllCurrencies()
    }
}