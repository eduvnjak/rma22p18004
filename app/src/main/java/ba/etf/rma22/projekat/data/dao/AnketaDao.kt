package ba.etf.rma22.projekat.data.dao

import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Anketa

interface AnketaDao {
    @Query("SELECT * FROM Anketa")
    suspend fun getAll()
    @Insert
    suspend fun insertAnkete(vararg ankete: Anketa)
}