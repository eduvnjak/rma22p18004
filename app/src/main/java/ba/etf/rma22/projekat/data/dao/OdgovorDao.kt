package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Odgovor

@Dao
interface OdgovorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOdgovor(vararg odgovor: Odgovor)

    @Query("SELECT * FROM Odgovor WHERE AnketaTakenId=:idPokusaja")
    fun getOdgovoriZaPokusaj(idPokusaja: Int): List<Odgovor>

    @Query("DELETE FROM Odgovor")
    fun deleteAll()
}