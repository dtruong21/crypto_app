package app.eleven.cryptonow.presentation.get_crypto_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.eleven.cryptonow.common.Resource
import app.eleven.cryptonow.domain.interactor.CryptoInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(private val interactor: CryptoInteractor) : ViewModel() {

	private val _uiState = MutableStateFlow(UiState())
	val uiState = _uiState.asStateFlow()

	internal fun getCryptos() {
		interactor.getCryptosUC().onEach { resource ->
			when (resource) {
				is Resource.Error -> _uiState.update {
					it.copy(
						isLoading = false,
						cryptos = emptyList(),
						error = resource.message ?: "Something happened"
					)
				}

				is Resource.Loading -> _uiState.update {
					it.copy(
						isLoading = true,
						cryptos = emptyList(),
						error = ""
					)
				}

				is Resource.Success -> _uiState.update {
					it.copy(
						isLoading = false,
						cryptos = resource.data ?: emptyList(),
						error = ""
					)
				}
			}
		}.launchIn(viewModelScope.plus(Dispatchers.IO))
	}

	private fun testing(id: String){

	}

	fun onEventChanged(event: CryptoEvent){
		when(event){
			CryptoEvent.OnButtonClicked -> getCryptos()
			is CryptoEvent.OnTest -> testing(event.id)
		}
	}
}