package app.eleven.cryptonow.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.eleven.cryptonow.domain.model.Crypto

@Entity
data class CryptoEntity(
	@PrimaryKey(autoGenerate = false) val id: String,
	val name: String,
	val value: String
)

fun CryptoEntity.toCrypto(): Crypto = Crypto(
	this.id,
	this.name,
	this.value
)
