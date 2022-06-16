package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Istrazivanje(
    @PrimaryKey @SerializedName("id") val id: Int,
    @ColumnInfo(name = "naziv") @SerializedName("naziv") val naziv: String,
    @ColumnInfo(name = "godina") @SerializedName("godina") val godina: Int){
    //    init {
//        require(godina in 1..5){
//            "godina mora biti izmedju 1 i 5 ukljucivo"
//        }
//    }
    override fun toString(): String {
        return naziv
    }
}
