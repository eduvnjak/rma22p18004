package ba.etf.rma22.projekat.data.dao

import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Istrazivanje

interface IstrazivanjeDao {
    @Insert
    suspend fun insertIstrazivanje(vararg istrazivanja: Istrazivanje)
    @Query("SELECT * FROM Istrazivanje")
    suspend fun getIstrazivanja(): List<Istrazivanje>
    @Query("SELECT * FROM Istrazivanje WHERE id=(:idIstrazivanja)")
    suspend fun getIstrazivanjeById(idIstrazivanja: Int): Istrazivanje?
    // ^dal ovo gore treba biti non null
    @Query("DELETE FROM Istrazivanje")
    suspend fun deleteAll()
}