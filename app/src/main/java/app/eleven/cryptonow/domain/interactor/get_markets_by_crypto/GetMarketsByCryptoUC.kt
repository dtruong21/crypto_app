package app.eleven.cryptonow.domain.interactor.get_markets_by_crypto

import app.eleven.cryptonow.common.Resource
import app.eleven.cryptonow.domain.model.Market
import app.eleven.cryptonow.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetMarketsByCryptoUC @Inject constructor(private val cryptoRepository: CryptoRepository) {

	operator fun invoke(id: String): Flow<Resource<List<Market>>> = flow {
		try {
			emit(Resource.Loading())
			val markets = cryptoRepository.getMarketByCrypto(id)
			emit(Resource.Success(markets))
		} catch (e: Exception) {
			emit(Resource.Error(message = "Error with ${e.localizedMessage}"))
		}
	}

}