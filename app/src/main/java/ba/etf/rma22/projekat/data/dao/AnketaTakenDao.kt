package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.AnketaTaken

@Dao
interface AnketaTakenDao {
    @Query("SELECT * FROM Anketataken")
    fun getAll(): List<AnketaTaken>?

    @Query("SELECT * FROM AnketaTaken WHERE AnketumId=:aid ORDER BY id DESC LIMIT 1")
    fun getAnketaTaken(aid: Int): AnketaTaken?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnketaTaken(vararg at: AnketaTaken)

    @Query("DELETE FROM AnketaTaken")
    fun deleteAll()

    @Query("UPDATE AnketaTaken SET progres=:noviProgres WHERE id=:idAt")
    fun updateProgres(idAt: Int, noviProgres: Int)
}