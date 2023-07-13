package app.eleven.cryptonow.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.eleven.cryptonow.data.local.entities.CryptoEntity

@Dao
interface CryptoDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertCryptos(cryptos: List<CryptoEntity>)

	@Query("SELECT * FROM CryptoEntity")
	fun getCryptos(): List<CryptoEntity>

	@Query("DELETE FROM CryptoEntity")
	fun deleteCryptos()
}