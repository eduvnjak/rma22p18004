package ba.etf.rma22.projekat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ba.etf.rma22.projekat.data.dao.*
import ba.etf.rma22.projekat.data.models.*

@Database(entities = [Account::class, Anketa::class, AnketaGrupa::class, AnketaTaken::class, Grupa::class, Istrazivanje::class, Odgovor::class, Pitanje::class],version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun anketaDao(): AnketaDao
    abstract fun anketaTakenDao(): AnketaTakenDao
    abstract fun grupaDao(): GrupaDao
    abstract fun istrazivanjeDao(): IstrazivanjeDao
    abstract fun odgovorDao(): OdgovorDao
    abstract fun pitanjeDao(): PitanjeDao
    abstract fun anketaGrupaDao(): AnketaGrupaDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        fun setInstance(db: AppDatabase) {
            INSTANCE = db
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "RMA22DB"
            ).build()
    }
}