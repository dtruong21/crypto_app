package app.eleven.cryptonow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.eleven.cryptonow.common.Resource
import app.eleven.cryptonow.domain.interactor.CryptoInteractor
import app.eleven.cryptonow.domain.interactor.get_cryptos.GetCryptosUC
import app.eleven.cryptonow.domain.interactor.get_markets_by_crypto.GetMarketsByCryptoUC
import app.eleven.cryptonow.domain.model.Crypto
import app.eleven.cryptonow.presentation.get_crypto_list.CryptoViewModel
import app.eleven.cryptonow.presentation.get_crypto_list.UiState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

class CryptoViewModelTest {

	@Rule
	@JvmField
	val rule = InstantTaskExecutorRule()

	private val getCryptosUC = mock<GetCryptosUC>()
	private val getMarketsByCryptoUC = mock<GetMarketsByCryptoUC>()

	private val interactor by lazy { CryptoInteractor(getCryptosUC, getMarketsByCryptoUC) }

	private val viewModel by lazy { CryptoViewModel(interactor) }

	@Test
	fun `get Crypto List with success`() {
		viewModel.getCryptos()
		whenever(interactor.getCryptosUC.invoke()).thenReturn(flow { emit(Resource.Success(emptyList())) })
		val uiState = viewModel.uiState.value
		assertEquals(uiState.cryptos, emptyList<Crypto>())
	}

	@Test
	fun `get Crypto List KO`() {
		whenever(interactor.getCryptosUC.invoke()).thenReturn(flow { emit(Resource.Error(message = "abc")) })
		viewModel.getCryptos()
		runBlockingTest {
			val uiState = viewModel.uiState.first()
			assertTrue(uiState.error.isNotEmpty())
		}
	}

	@Test
	fun `get Crypto List loading`() {
		whenever(interactor.getCryptosUC.invoke()).thenReturn(flow { emit(Resource.Loading()) })
		viewModel.getCryptos()
		runBlockingTest {
			val uiState = viewModel.uiState.first()
			assertEquals(true,uiState.isLoading)
		}
	}
}