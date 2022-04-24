package ba.etf.rma22.projekat.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel

class FragmentPitanje(val pitanje: Pitanje, val anketa: Anketa): Fragment() {
    private lateinit var listaOdgovora: ListView
    private lateinit var tekstPitanja: TextView
    private lateinit var dugmeZaustavi: Button

    private lateinit var listaOdgovoraAdapter: ArrayAdapter<String>

    private val pitanjeAnketaViewModel = PitanjeAnketaViewModel()

    companion object {
        fun newInstance(pitanje: Pitanje, anketa: Anketa) = FragmentPitanje(pitanje, anketa)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pitanje, container, false)

        listaOdgovora = view.findViewById(R.id.odgovoriLista)
        tekstPitanja = view.findViewById(R.id.tekstPitanja)
        dugmeZaustavi = view.findViewById(R.id.dugmeZaustavi)


        listaOdgovoraAdapter =  OdgovoriListaAdapter(requireActivity(), R.layout.lista_pitanja_element, pitanje, anketa, false, this)
        listaOdgovora.adapter = listaOdgovoraAdapter

//        var odabranaOpcija: Int? = pitanjeAnketaViewModel.dajPitanjeAnketuZaAnketu(anketa, pitanje).odabranaOpcija
//        if(odabranaOpcija != null){
//            val odabraniElement = (getViewByPosition(odabranaOpcija, listaOdgovora) as TextView)
//            odabraniElement.setTextColor(Color.parseColor("#0000FF"))
////            (getViewByPosition(odabranaOpcija, listaOdgovora) as TextView).text = "zatoooooo"
////            tekstPitanja.setTextColor(Color.parseColor("#0000FF"))
////            Log.i("Test",
////                (getViewByPosition(odabranaOpcija, listaOdgovora) as TextView).text as String
////            )
//        }

        tekstPitanja.text = pitanje.tekst
        dugmeZaustavi.setOnClickListener{
            zaustaviAnketu()
        }

        return view
        }

    private fun zaustaviAnketu() {
        val poruka = "Završili ste anketu ${anketa.naziv} u okviru istraživanja ${anketa.nazivIstrazivanja}"
        (activity as MainActivity).prikaziPorukuZaustaviAnketu(poruka)
    }

    fun azurirajProgres() {
        //izracunaj novi
        pitanjeAnketaViewModel.azurirajProgres(anketa)
        //posalji obavijestu main activity da izmijeni u fragmentPredaj
        (activity as MainActivity).azurirajProgresUFragmentu()
    }
}