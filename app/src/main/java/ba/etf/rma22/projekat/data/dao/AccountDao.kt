package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Account

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(acc: Account)

    @Query("SELECT * FROM Account")
    fun getAll(): List<Account>

    @Query("SELECT acHash FROM Account WHERE id=0")
    fun getHash(): String

    @Query("UPDATE Account SET acHash=:noviHash WHERE id=0")
    fun updateHash(noviHash: String)
}