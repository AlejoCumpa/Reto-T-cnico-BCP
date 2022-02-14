package cumpa.alejandro.retotcnicobcp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cumpa.alejandro.retotcnicobcp.domain.ListAllCurrencies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val listAllCurrencies: ListAllCurrencies
): ViewModel(){

    val isLoading = MutableLiveData<Boolean>()
    val finishedFetching = MutableLiveData<Boolean>()

    fun onCreate() {
        finishedFetching.postValue(false)
        viewModelScope.launch {
            isLoading.postValue(true)

            listAllCurrencies()

            isLoading.postValue(false)
            finishedFetching.postValue(true)
        }
    }
}