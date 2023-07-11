package app.eleven.cryptonow.presentation

sealed class Screen(val route: String){
	object MainScreen: Screen("main")
	object MarketScreen: Screen("market")
}
