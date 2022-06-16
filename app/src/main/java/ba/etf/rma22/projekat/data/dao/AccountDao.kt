package ba.etf.rma22.projekat.data.dao

import androidx.room.Insert
import ba.etf.rma22.projekat.data.models.Account

interface AccountDao {

    @Insert
    suspend fun insertAccount(acc: Account)
}