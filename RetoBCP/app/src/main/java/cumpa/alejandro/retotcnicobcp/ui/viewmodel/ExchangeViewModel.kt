package cumpa.alejandro.retotcnicobcp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cumpa.alejandro.retotcnicobcp.di.DEFAULT_AMOUNT
import cumpa.alejandro.retotcnicobcp.domain.GetCurrencyByCode
import cumpa.alejandro.retotcnicobcp.domain.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Math.round
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val getCurrencyByCode: GetCurrencyByCode
): ViewModel(){

    val sendCurrency = MutableLiveData<Currency>()
    val getCurrency = MutableLiveData<Currency>()
    val sendAmount = MutableLiveData<Float>()
    val getAmount = MutableLiveData<Float>()
    val buySellText = MutableLiveData<String>()

    var amountSending = DEFAULT_AMOUNT
    lateinit var curSending: Currency
    lateinit var curGetting: Currency

     fun onCreate(){
        viewModelScope.launch {
            curGetting = getCurrencyByCode("PEN")
            curSending = getCurrencyByCode("USD")
            calculate()
        }
    }

    fun UpdateAmount(etValue: String){
        amountSending =  etValue.toFloat()

        val resultAmount :Float = amountSending * curSending.value / curGetting.value
        getAmount.postValue( round(resultAmount*100) /100 .toFloat())
        getCurrency.postValue(curGetting)

    }

    fun UpdateSendCurrency(newCurrency: Currency){
        curSending = newCurrency
        sendCurrency.postValue(newCurrency)
    }
    fun UpdateGetCurrency(newCurrency: Currency){
        curGetting = newCurrency
        getCurrency.postValue(newCurrency)
    }

    fun changeCurrencies(){
        var auxCurrency = curGetting
        curGetting = curSending
        curSending = auxCurrency
        calculate()
    }

    fun calculate(){
        val resultAmount :Float = amountSending * (curSending.value+ 0.01f) / curGetting.value // +0.01 de Euro
        val resultSellText = (curSending.value + 0.01f)/ curGetting.value
        val resultBuyText = curSending.value / curGetting.value

        getAmount.postValue( round(resultAmount*100) /100 .toFloat())
        getCurrency.postValue(curGetting)
        sendCurrency.postValue(curSending)
        sendAmount.postValue(amountSending)
        buySellText.postValue("Compra: ${round(resultBuyText*10000) /10000 .toFloat()} |  Venta: ${round(resultSellText*10000) /10000 .toFloat()}")
    }

}