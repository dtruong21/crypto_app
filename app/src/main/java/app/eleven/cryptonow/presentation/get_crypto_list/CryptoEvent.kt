package app.eleven.cryptonow.presentation.get_crypto_list

sealed class CryptoEvent {
	object OnButtonClicked : CryptoEvent()
	data class OnTest(val id: String): CryptoEvent()
}
