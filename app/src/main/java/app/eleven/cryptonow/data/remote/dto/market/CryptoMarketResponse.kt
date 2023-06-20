package app.eleven.cryptonow.data.remote.dto.market


import com.squareup.moshi.Json

data class CryptoMarketResponse(
	@Json(name = "data")
    val `data`: List<MarketDto>?,
	@Json(name = "timestamp")
    val timestamp: Long?
)