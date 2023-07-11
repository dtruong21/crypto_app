package app.eleven.cryptonow.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.eleven.cryptonow.presentation.get_crypto_list.CryptoScreen
import app.eleven.cryptonow.presentation.get_market_list.MarketScreen
import app.eleven.cryptonow.presentation.ui.theme.CryptoNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			CryptoNowTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					MainScreen()
				}
			}
		}
	}
}

@Composable
fun MainScreen() {
	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
		composable(Screen.MainScreen.route) {
			CryptoScreen{
				navController.navigate(Screen.MarketScreen.route)
			}
		}
		composable(Screen.MarketScreen.route){
			MarketScreen(id = "")
		}
	}
}
