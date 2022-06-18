package ba.etf.rma22.projekat.data.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.AnketaGrupa

interface AnketaGrupaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnketaGrupa(vararg anketaGrupa: AnketaGrupa)
    @Query("SELECT * FROM AnketaGrupa WHERE anketaId=(:idAnkete)")
    suspend fun getAnketaGrupaZaAnketu(idAnkete: Int): List<AnketaGrupa>
}