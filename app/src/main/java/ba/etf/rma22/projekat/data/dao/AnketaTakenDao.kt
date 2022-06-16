package ba.etf.rma22.projekat.data.dao

import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.AnketaTaken

interface AnketaTakenDao {
    @Query("SELECT * FROM AnketaTaken WHERE id=(:atid)")
    suspend fun getAnketaTaken(atid: Int)
    @Insert
    suspend fun insertAnketaTaken(at: AnketaTaken)

    @Query("DELETE FROM AnketaTaken")
    suspend fun deleteAll()
}