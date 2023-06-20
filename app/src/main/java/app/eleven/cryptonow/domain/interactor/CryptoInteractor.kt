package app.eleven.cryptonow.domain.interactor

import app.eleven.cryptonow.domain.interactor.get_cryptos.GetCryptosUC
import app.eleven.cryptonow.domain.interactor.get_markets_by_crypto.GetMarketsByCryptoUC
import javax.inject.Inject

data class CryptoInteractor @Inject constructor(
	val getCryptosUC: GetCryptosUC,
	val getMarketsByCryptoUC: GetMarketsByCryptoUC
)
