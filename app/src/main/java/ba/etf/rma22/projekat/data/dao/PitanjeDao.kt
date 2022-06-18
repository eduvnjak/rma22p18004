package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Pitanje

@Dao
interface PitanjeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPitanje(vararg pitanja: Pitanje)

    @Query("DELETE FROM Pitanje")
    suspend fun deleteAll()

    @Query("SELECT * FROM Pitanje WHERE anketaId=:idAnkete")
    suspend fun getPitanjaZaAnketu(idAnkete: Int): List<Pitanje>
}