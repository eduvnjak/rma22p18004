package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["anketaId", "grupaId"])
data class AnketaGrupa(@ColumnInfo(name = "anketaId") val anketaId: Int,
                       @ColumnInfo(name = "grupaId") val grupaId: Int)
