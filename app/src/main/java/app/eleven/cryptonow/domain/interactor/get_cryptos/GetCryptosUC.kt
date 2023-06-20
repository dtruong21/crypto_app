package app.eleven.cryptonow.domain.interactor.get_cryptos

import app.eleven.cryptonow.common.Resource
import app.eleven.cryptonow.domain.model.Crypto
import app.eleven.cryptonow.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCryptosUC @Inject constructor(private val repository: CryptoRepository) {
	operator fun invoke(): Flow<Resource<List<Crypto>>> = flow {
		try {
			emit(Resource.Loading())
			val cryptos = repository.getCryptos()
			emit(Resource.Success(cryptos))
		} catch (e: Exception) {
			emit(Resource.Error(message = "Error with ${e.localizedMessage}"))
		}
	}
}