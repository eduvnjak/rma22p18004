package ba.etf.rma22.projekat.data.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Account

interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(acc: Account)
    @Query("SELECT * FROM Account")
    suspend fun getAll(): List<Account>
    @Query("SELECT acHash FROM Account WHERE id=0")
    suspend fun getHash(): String
    @Query("UPDATE Account SET acHash=(:noviHash) WHERE id=0")
    suspend fun updateHash(noviHash: String)
}