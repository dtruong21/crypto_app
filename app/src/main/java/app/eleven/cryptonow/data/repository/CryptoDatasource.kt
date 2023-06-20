package app.eleven.cryptonow.data.repository

import app.eleven.cryptonow.data.remote.api.CryptoApi
import app.eleven.cryptonow.data.remote.dto.asset.toCrypto
import app.eleven.cryptonow.data.remote.dto.market.toMarket
import app.eleven.cryptonow.domain.model.Crypto
import app.eleven.cryptonow.domain.model.Market
import app.eleven.cryptonow.domain.repository.CryptoRepository
import javax.inject.Inject

class CryptoDatasource @Inject constructor(private val cryptoApi: CryptoApi) : CryptoRepository {
	override suspend fun getCryptos(): List<Crypto> = cryptoApi.getCryptos().data?.map {
		it.toCrypto()
	} ?: emptyList()

	override suspend fun getMarketByCrypto(id: String): List<Market> =
		cryptoApi.getCryptoMarkets(id).data?.map {
			it.toMarket()
		} ?: emptyList()
}