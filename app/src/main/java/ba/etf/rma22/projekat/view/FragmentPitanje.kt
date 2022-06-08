package ba.etf.rma22.projekat.view

import android.os.Bundle
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
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel

class FragmentPitanje(val pitanje: Pitanje, var odgovorIndeks: Int?, var pregled: Boolean): Fragment() {
    private lateinit var listaOdgovora: ListView
    private lateinit var tekstPitanja: TextView
    private lateinit var dugmeZaustavi: Button
    val samoPregled = (odgovorIndeks != null)

    private lateinit var listaOdgovoraAdapter: ArrayAdapter<String>

    private val pitanjeAnketaViewModel = PitanjeAnketaViewModel()

    companion object {
        fun newInstance(pitanje: Pitanje, odgovorIndeks: Int?, pregled: Boolean) = FragmentPitanje(pitanje, odgovorIndeks, pregled)
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

        listaOdgovoraAdapter =  OdgovoriListaAdapter(requireActivity(), R.layout.lista_pitanja_element, pitanje, odgovorIndeks, samoPregled, this)
        listaOdgovora.adapter = listaOdgovoraAdapter


        tekstPitanja.text = pitanje.tekstPitanja
        dugmeZaustavi.setOnClickListener{
            zaustaviAnketu()
        }

        return view
        }

    private fun zaustaviAnketu() {
        (activity as MainActivity).zaustaviAnketu()
    }

    fun azurirajProgres() {
        //posalji obavijestu main activity da izmijeni u fragmentPredaj
        (activity as MainActivity).azurirajProgresUFragmentu()
    }
}