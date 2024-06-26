package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Anketa

@Dao
interface AnketaDao {
    @Query("SELECT * FROM Anketa")
    fun getAll(): List<Anketa>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnkete(vararg ankete: Anketa)

    @Query("DELETE FROM Anketa")
    fun deleteAll()

    @Query("SELECT * FROM Anketa WHERE upisana=1")
    fun getUpisane(): List<Anketa>
    //todo dal ove select u slucaju prazne tabele vracaju null ili praznu listu
}