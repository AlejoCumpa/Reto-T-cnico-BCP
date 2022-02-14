package cumpa.alejandro.retotcnicobcp.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyModel (
    @SerializedName("name") val name: String,
    @SerializedName("flag") val flag: String,
    @SerializedName("currency_code") val currency_code: String,
    @SerializedName("currency_name") val currency_name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("value") val value: Float,
)