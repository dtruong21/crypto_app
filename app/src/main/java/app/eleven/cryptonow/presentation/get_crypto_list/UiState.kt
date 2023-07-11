package app.eleven.cryptonow.presentation.get_crypto_list

import app.eleven.cryptonow.domain.model.Crypto

data class UiState(
	val isLoading: Boolean = false,
	val cryptos: List<Crypto> = emptyList(),
	val error: String = ""
)
