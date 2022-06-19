package ba.etf.rma22.projekat.view

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel

class OdgovoriListaAdapter(context: Context, @LayoutRes val layoutResource: Int,
                           val pitanje: Pitanje, var odgovorIndeks: Int?,
                           val onemoguci: Boolean, var fragmentPitanje: FragmentPitanje): ArrayAdapter<String>(context, layoutResource, pitanje.opcije)  {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val tekstOdgovora = view.findViewById<TextView>(android.R.id.text1)

        if(!onemoguci){
            view.setOnClickListener() {
                for (i in 0 until parent.childCount){
                    if (i == position){
                        if((parent.getChildAt(i) as TextView).currentTextColor == Color.parseColor("#FF000000")){
                            (parent.getChildAt(i) as TextView).setTextColor(Color.parseColor("#0000FF"))
                            odgovorIndeks = position
                            fragmentPitanje.odgovorIndeks = position
                            //Log.i("ODGOVORIO na", " " + odgovorIndeks + " ")
                        }else{
                            (parent.getChildAt(i) as TextView).setTextColor(Color.parseColor("#FF000000"))
                            odgovorIndeks = null
                            fragmentPitanje.odgovorIndeks = null
                        }
                        fragmentPitanje.azurirajProgres()
                    }else{
                        (parent.getChildAt(i) as TextView).setTextColor(Color.parseColor("#FF000000"))
                    }
                }
            }
        }else{
            //vidi da li je odgovoreno i oznaci odgovor ako jeste
            for (i in 0 until parent.childCount) {
                //Log.i("TESt", "pozicija " + position + " odgovor " + odgovorIndeks)
                if (i == odgovorIndeks) (parent.getChildAt(odgovorIndeks!!) as TextView).setTextColor(
                    Color.parseColor("#0000FF")
                )
            }
        }

        return view
    }
}