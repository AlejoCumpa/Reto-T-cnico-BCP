package cumpa.alejandro.retotcnicobcp.domain.model

import cumpa.alejandro.retotcnicobcp.data.database.entities.CurrencyEntity
import cumpa.alejandro.retotcnicobcp.data.model.CurrencyModel
import java.io.Serializable

data class Currency (val name:String,
                    val flag:String,
                    val currency_name:String,
                    val currency_code:String,
                    val currency_symbol:String,
                    val value:Float): Serializable
fun CurrencyModel.toDomain() = Currency(name, flag, currency_name, currency_code, symbol, value)
fun CurrencyEntity.toDomain() = Currency(name, flag, currency_name, currency_code, symbol,value)