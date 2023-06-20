package app.eleven.cryptonow.di

import app.eleven.cryptonow.common.BASE_URL
import app.eleven.cryptonow.data.remote.api.CryptoApi
import app.eleven.cryptonow.data.repository.CryptoDatasource
import app.eleven.cryptonow.domain.repository.CryptoRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	@Singleton
	fun provideCryptoApi(): CryptoApi = Retrofit.Builder()
		.baseUrl(BASE_URL)
		.addConverterFactory(
			MoshiConverterFactory.create(
				Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
			)
		)
		.addCallAdapterFactory(CoroutineCallAdapterFactory())
		.build()
		.create(CryptoApi::class.java)

	@Provides
	@Singleton
	fun provideCryptoRepository(cryptoApi: CryptoApi): CryptoRepository =
		CryptoDatasource(cryptoApi)

}
