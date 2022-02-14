package cumpa.alejandro.retotcnicobcp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cumpa.alejandro.retotcnicobcp.data.database.dao.CurrencyDao
import cumpa.alejandro.retotcnicobcp.data.database.entities.CurrencyEntity

@Database(
    entities = [CurrencyEntity::class],
    version = 1
)
abstract class CurrencyDatabase: RoomDatabase() {

    abstract fun getCurrencyDao(): CurrencyDao
}