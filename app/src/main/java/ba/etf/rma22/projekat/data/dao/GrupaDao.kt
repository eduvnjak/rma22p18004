package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Grupa

@Dao
interface GrupaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGrupa(vararg grupe: Grupa)

    @Query("SELECT * FROM Grupa")
    fun getGrupe(): List<Grupa>

    @Query("SELECT * FROM Grupa WHERE IstrazivanjeId=:idIstrazivanja")
    fun getGrupeZaIstrazivanje(idIstrazivanja: Int): List<Grupa>
    //jel ovo dobro ispod ovo upisan = 1

    @Query("UPDATE Grupa SET upisana=1 WHERE id=:grupaId")
    fun upisiUGrupu(grupaId: Int)

    @Query("DELETE FROM Grupa")
    fun deleteAll()

    @Query("SELECT * FROM Grupa WHERE upisana=1")
    fun getUpisaneGrupe(): List<Grupa>

    @Query("SELECT * FROM Grupa WHERE id=:grupaId")
    fun getGrupaById(grupaId: Int): Grupa
}