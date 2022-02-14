package cumpa.alejandro.retotcnicobcp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cumpa.alejandro.retotcnicobcp.data.model.CurrencyModel
import cumpa.alejandro.retotcnicobcp.domain.model.Currency

@Entity(tableName = "currency_table")
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "flag") val flag: String,
    @ColumnInfo(name = "currency_code") val currency_code: String,
    @ColumnInfo(name = "currency_name") val currency_name: String,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "value") val value: Float,
)

fun Currency.toDatabase() = CurrencyEntity(
    name = name,
    flag = flag,
    currency_code =  currency_code,
    currency_name =  currency_name,
    symbol =  currency_symbol,
    value = value)
