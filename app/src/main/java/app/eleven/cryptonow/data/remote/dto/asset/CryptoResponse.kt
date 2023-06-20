package app.eleven.cryptonow.data.remote.dto.asset


import com.squareup.moshi.Json

data class CryptoResponse(
    @Json(name = "data")
    val `data`: List<CryptoDto>?,
    @Json(name = "timestamp")
    val timestamp: Long?
)