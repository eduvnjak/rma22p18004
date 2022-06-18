package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.AnketaTaken

@Dao
interface AnketaTakenDao {
    @Query("SELECT * FROM Anketataken")
    suspend fun getAll(): List<AnketaTaken>?
    @Query("SELECT * FROM AnketaTaken WHERE id=(:atid) ORDER BY id DESC LIMIT 1")
    suspend fun getAnketaTaken(atid: Int): AnketaTaken?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnketaTaken(vararg at: AnketaTaken)
    @Query("DELETE FROM AnketaTaken")
    suspend fun deleteAll()
    @Query("UPDATE AnketaTaken SET progres=(:noviProgres) WHERE id=(:idAt)")
    suspend fun updateProgres(idAt: Int, noviProgres: Int)
}