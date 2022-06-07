package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.viewmodel.AnketeViewModel

class FragmentPredaj(val anketa: Anketa, val pregled: Boolean): Fragment() {
    private lateinit var progresTekst: TextView
    private lateinit var dugmePredaj: Button

    private var anketeViewModel = AnketeViewModel()

    companion object{
        fun newInstance(anketa: Anketa, pregled: Boolean) = FragmentPredaj(anketa, pregled)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_predaj, container,false)

        progresTekst = view.findViewById(R.id.progresTekst)
        dugmePredaj = view.findViewById(R.id.dugmePredaj)

        //progresTekst.text = anketeViewModel.dajProgres(anketa)
        progresTekst.text = "TODO"

        dugmePredaj.isEnabled = !pregled
        dugmePredaj.setOnClickListener{
//            predajAnketu()
        }

        return view
    }

//    private fun predajAnketu() {
//        anketeViewModel.proglasiAnketuUradjenom(anketa)
//        val poruka = "Završili ste anketu ${anketa.naziv} u okviru istraživanja ${anketa.nazivIstrazivanja}"
//        (activity as MainActivity).predajAnketuPrikaziPoruku(poruka)
//    }
//    fun updateProgress() {
////        Log.i("TAGTAG","fragment predaj")
//        if(this::progresTekst.isInitialized){
////            Log.i("TAGTAG","fragment predaj mijenjam" + anketeViewModel.dajProgres(anketa))
//            progresTekst.text = anketeViewModel.dajProgres(anketa)
//        }
//    }
}