package cumpa.alejandro.retotcnicobcp.data.network


import cumpa.alejandro.retotcnicobcp.data.model.CurrencyModel
import retrofit2.Response
import retrofit2.http.GET


interface CurrencyAPIClient  {
    @GET("currencies.json?alt=media")
    suspend fun getAllCurrencies(): Response<List<CurrencyModel>>
}