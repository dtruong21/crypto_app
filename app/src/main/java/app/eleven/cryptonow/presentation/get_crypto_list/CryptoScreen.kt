package app.eleven.cryptonow.presentation.get_crypto_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.eleven.cryptonow.R
import app.eleven.cryptonow.domain.model.Crypto

@Composable
fun CryptoScreen(viewModel: CryptoViewModel = hiltViewModel(), onItemClick: (id: String) -> Unit) {
	val uiState = viewModel.uiState.collectAsStateWithLifecycle()
	CryptoScreenContent(
		uiState = uiState.value,
		onButtonClick = {
			viewModel.onEventChanged(CryptoEvent.OnButtonClicked)
		}
	) {
		onItemClick(it)
	}
}

@Composable
fun CryptoScreenContent(
	uiState: UiState,
	onButtonClick: () -> Unit,
	onItemClick: (id: String) -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp)
	) {
		Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
			Button(onClick = { onButtonClick() }, modifier = Modifier.fillMaxWidth()) {
				Text(text = stringResource(R.string.load_crypto))
			}
		}

		when {
			uiState.isLoading -> {

			}

			uiState.cryptos.isEmpty() -> {

			}

			uiState.cryptos.isNotEmpty() -> {
				LazyColumn {
					items(uiState.cryptos) {
						CryptoItemList(crypto = it) { id ->
							onItemClick(id)
						}
					}
				}
			}

			uiState.error.isNotEmpty() -> {

			}
		}

	}

}

@Composable
fun CryptoItemList(crypto: Crypto, onItemClick: (id: String) -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(IntrinsicSize.Max)
			.clickable {
				onItemClick(crypto.id)
			},
		verticalAlignment = Alignment.CenterVertically
	) {
		Column(
			modifier = Modifier.padding(8.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.SpaceAround
		) {
			Text(text = crypto.symbol)
			Text(text = crypto.id)
		}
		Spacer(modifier = Modifier.weight(1f))
		Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center) {
			Text(text = crypto.price + "$")
		}
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewCryptoItem() =
	CryptoItemList(crypto = Crypto("Bitcoin", "BTC", "268000"), onItemClick = {})
