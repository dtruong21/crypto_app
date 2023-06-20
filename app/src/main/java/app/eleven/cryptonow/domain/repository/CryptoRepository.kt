package app.eleven.cryptonow.domain.repository

import app.eleven.cryptonow.domain.model.Crypto
import app.eleven.cryptonow.domain.model.Market

interface CryptoRepository {
	suspend fun getCryptos(): List<Crypto>
	suspend fun getMarketByCrypto(id: String): List<Market>
}