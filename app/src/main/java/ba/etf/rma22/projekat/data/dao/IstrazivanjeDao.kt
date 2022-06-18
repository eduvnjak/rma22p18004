package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Istrazivanje

@Dao
interface IstrazivanjeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIstrazivanje(vararg istrazivanja: Istrazivanje)

    @Query("SELECT * FROM Istrazivanje")
    fun getIstrazivanja(): List<Istrazivanje>

    @Query("SELECT * FROM Istrazivanje WHERE id=:idIstrazivanja")
    fun getIstrazivanjeById(idIstrazivanja: Int): Istrazivanje?
    // ^dal ovo gore treba biti non null

    @Query("DELETE FROM Istrazivanje")
    fun deleteAll()
}