package cumpa.alejandro.retotcnicobcp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import cumpa.alejandro.retotcnicobcp.data.network.CurrencyAPIClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltProviderModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com/v0/b/reto-bcp-887a2.appspot.com/o/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyApiClient(retrofit: Retrofit):CurrencyAPIClient{
        return retrofit.create(CurrencyAPIClient::class.java)
    }

    @Singleton
    @Provides
    fun provideGlide(@ActivityContext context: Context): RequestManager{
        return Glide.with(context)
    }
}