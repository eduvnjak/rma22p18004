package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.AnketaGrupa

@Dao
interface AnketaGrupaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnketaGrupa(vararg anketaGrupa: AnketaGrupa)

    @Query("SELECT * FROM AnketaGrupa WHERE anketaId=:idAnkete")
    fun getAnketaGrupaZaAnketu(idAnkete: Int): List<AnketaGrupa>

    @Query("DELETE FROM AnketaGrupa")
    fun deleteAll()
}