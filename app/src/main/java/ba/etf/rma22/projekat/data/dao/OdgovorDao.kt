package ba.etf.rma22.projekat.data.dao

import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Odgovor

interface OdgovorDao {
    @Insert
    suspend fun insertOdgovor(odgovor: Odgovor)

    @Query("DELETE FROM Odgovor")
    suspend fun deleteAll()
}