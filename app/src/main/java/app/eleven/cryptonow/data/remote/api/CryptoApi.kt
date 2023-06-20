package app.eleven.cryptonow.data.remote.api

import app.eleven.cryptonow.data.remote.dto.asset.CryptoResponse
import app.eleven.cryptonow.data.remote.dto.market.CryptoMarketResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoApi {

	@GET("/assets")
	suspend fun getCryptos(): CryptoResponse

	@GET("/assets/{id}/markets")
	suspend fun getCryptoMarkets(@Path("id") id: String): CryptoMarketResponse

}