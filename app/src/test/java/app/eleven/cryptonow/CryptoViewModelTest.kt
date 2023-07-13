package app.eleven.cryptonow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.eleven.cryptonow.common.Resource
import app.eleven.cryptonow.domain.interactor.CryptoInteractor
import app.eleven.cryptonow.domain.interactor.get_cryptos.GetCryptosUC
import app.eleven.cryptonow.domain.interactor.get_markets_by_crypto.GetMarketsByCryptoUC
import app.eleven.cryptonow.domain.model.Crypto
import app.eleven.cryptonow.presentation.get_crypto_list.CryptoViewModel
import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Assert.*

class CryptoViewModelTest {

	@Rule
	@JvmField
	val rule = InstantTaskExecutorRule()

	private val getCryptosUC = mock<GetCryptosUC>()
	private val getMarketsByCryptoUC = mock<GetMarketsByCryptoUC>()

	private val interactor by lazy { CryptoInteractor(getCryptosUC, getMarketsByCryptoUC) }

	private val viewModel by lazy { CryptoViewModel(interactor) }

	fun `get Crypto List with success`() {
		whenever(interactor.getCryptosUC.invoke()).thenReturn(flow { emit(Resource.Success(emptyList())) })
		viewModel.getCryptos()
		val uiState = viewModel.uiState.value
		assertEquals(uiState.cryptos, emptyList<Crypto>())
	}

	fun `get Crypto List KO`() {
		whenever(interactor.getCryptosUC.invoke()).thenReturn(flow { emit(Resource.Error(message = "abc")) })
		viewModel.getCryptos()
		val uiState = viewModel.uiState.value
		assertTrue(uiState.error.isNotEmpty())
	}

	fun `get Crypto List loading`() {

	}
}