package cumpa.alejandro.retotcnicobcp.di

import android.content.Context
import androidx.room.Room
import cumpa.alejandro.retotcnicobcp.data.database.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "currencies_db"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CurrencyDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideCurrencyDao(db: CurrencyDatabase) = db.getCurrencyDao()
}