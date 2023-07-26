package app.eleven.cryptonow.framework

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.eleven.cryptonow.data.local.dao.CryptoDao
import app.eleven.cryptonow.data.local.entities.CryptoEntity

@Database(
	version = 2,
	exportSchema = false,
	entities = [
		CryptoEntity::class
	]
)
abstract class AppDatabase : RoomDatabase() {
	companion object {
		private var INSTANCE: AppDatabase? = null

		fun init(context: Context) {
			if (INSTANCE == null) {
				INSTANCE = Room.databaseBuilder(
					context.applicationContext, AppDatabase::class.java,
					"crypto_database"
				)
					.allowMainThreadQueries()
					.fallbackToDestructiveMigration()
					.build()
			}
		}

		fun getInstance(): AppDatabase {
			return INSTANCE!!
		}
	}

	abstract fun getCryptoDao(): CryptoDao
}
