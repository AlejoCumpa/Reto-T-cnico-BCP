package cumpa.alejandro.retotcnicobcp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cumpa.alejandro.retotcnicobcp.domain.ListAllCurrenciesDB
import cumpa.alejandro.retotcnicobcp.domain.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val listAllCurrenciesDB: ListAllCurrenciesDB
):ViewModel(){

    val currenciesModel = MutableLiveData<List<Currency>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = listAllCurrenciesDB()
            if (!result.isNullOrEmpty()) {
                Log.d("ViewModel", "Currencies: $result")
                currenciesModel.postValue(result)
                isLoading.postValue(false)
            }
            else{
                Log.d("ViewModel", "NullOrEmpty")
            }
        }
    }
}