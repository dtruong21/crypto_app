package app.eleven.cryptonow.data.repository

import app.eleven.cryptonow.data.local.entities.toCrypto
import app.eleven.cryptonow.data.remote.api.CryptoApi
import app.eleven.cryptonow.data.remote.dto.asset.toCrypto
import app.eleven.cryptonow.data.remote.dto.asset.toCryptoEntity
import app.eleven.cryptonow.data.remote.dto.market.toMarket
import app.eleven.cryptonow.domain.model.Crypto
import app.eleven.cryptonow.domain.model.Market
import app.eleven.cryptonow.domain.repository.CryptoRepository
import app.eleven.cryptonow.framework.AppDatabase
import javax.inject.Inject

class CryptoDatasource @Inject constructor(
	private val cryptoApi: CryptoApi,
	private val database: AppDatabase
) : CryptoRepository {
	override suspend fun getCryptos(): List<Crypto> {
		val cryptoList = cryptoApi.getCryptos().data
		if (cryptoList?.isNotEmpty() == true) {
			database.getCryptoDao().deleteCryptos()
			database.getCryptoDao().insertCryptos(
				cryptoList.map {
					it.toCryptoEntity()
				}
			)
		}

		return database.getCryptoDao().getCryptos().map { it.toCrypto() }
	}

	override suspend fun getMarketByCrypto(id: String): List<Market> =
		cryptoApi.getCryptoMarkets(id).data?.map {
			it.toMarket()
		} ?: emptyList()
}