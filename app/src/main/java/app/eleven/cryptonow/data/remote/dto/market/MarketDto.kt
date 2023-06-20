package app.eleven.cryptonow.data.remote.dto.market


import app.eleven.cryptonow.domain.model.Market
import com.squareup.moshi.Json

data class MarketDto(
	@Json(name = "baseId")
	val baseId: String?,
	@Json(name = "baseSymbol")
	val baseSymbol: String?,
	@Json(name = "exchangeId")
	val exchangeId: String?,
	@Json(name = "priceUsd")
	val priceUsd: String?,
	@Json(name = "quoteId")
	val quoteId: String?,
	@Json(name = "quoteSymbol")
	val quoteSymbol: String?,
	@Json(name = "volumePercent")
	val volumePercent: String?,
	@Json(name = "volumeUsd24Hr")
	val volumeUsd24Hr: String?
)

fun MarketDto.toMarket(): Market = Market(
	name = exchangeId ?: "",
	volume = volumeUsd24Hr ?: ""
)