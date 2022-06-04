package ba.etf.rma22.projekat.view

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel

class OdgovoriListaAdapter(context: Context, @LayoutRes val layoutResource: Int,
                           val pitanje: Pitanje, val anketa: Anketa,
                           val onemoguci: Boolean, var fragmentPitanje: FragmentPitanje): ArrayAdapter<String>(context, layoutResource, pitanje.opcije)  {

//    private val pitanjeAnketaViewModel = PitanjeAnketaViewModel()
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val view = super.getView(position, convertView, parent)
//        val tekstOdgovora = view.findViewById<TextView>(android.R.id.text1)
//        val pitanjeAnketa = pitanjeAnketaViewModel.dajPitanjeAnketuZaAnketu(anketa, pitanje)
//
//        if(pitanjeAnketa.odabranaOpcija != null && position == pitanjeAnketa.odabranaOpcija){
//            tekstOdgovora.setTextColor(Color.parseColor("#0000FF"))
//        }else{
//            tekstOdgovora.setTextColor(Color.parseColor("#FF000000"))
//        }
//        if(!onemoguci) {
//            view.setOnClickListener() {
//                for (i in 0 until parent.childCount){
//                    if (i == position){
//                        if((parent.getChildAt(i) as TextView).currentTextColor == Color.parseColor("#FF000000")){
//                            (parent.getChildAt(i) as TextView).setTextColor(Color.parseColor("#0000FF"))
//                            pitanjeAnketaViewModel.azurirajOdgovor(pitanjeAnketa,position)
//                        }else{
//                            (parent.getChildAt(i) as TextView).setTextColor(Color.parseColor("#FF000000"))
//                            pitanjeAnketaViewModel.azurirajOdgovor(pitanjeAnketa,null)
//                        }
//                        fragmentPitanje.azurirajProgres()
//                    }else{
//                        (parent.getChildAt(i) as TextView).setTextColor(Color.parseColor("#FF000000"))
//                    }
//                }
//            }
//        }
//        return view
//    }
}