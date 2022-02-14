package cumpa.alejandro.retotcnicobcp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cumpa.alejandro.retotcnicobcp.data.database.entities.CurrencyEntity

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency_table ORDER BY id DESC")
    suspend fun getALlCurrencies():List<CurrencyEntity>

    @Query("SELECT * FROM currency_table where currency_code = :currency_code")
    suspend fun getCurrencyById(currency_code : String):CurrencyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies:List<CurrencyEntity>)

    @Query("DELETE FROM currency_table")
    suspend fun deleteAllCurrencies()
}