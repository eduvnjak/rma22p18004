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
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel

class FragmentPitanje(val pitanje: Pitanje): Fragment() {
    private lateinit var listaOdgovora: ListView
    private lateinit var tekstPitanja: TextView
    private lateinit var dugmeZaustavi: Button


    private lateinit var listaOdgovoraAdapter: ArrayAdapter<String>

    companion object {
        fun newInstance(pitanje: Pitanje) = FragmentPitanje(pitanje)
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


        listaOdgovoraAdapter =  ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1, pitanje.opcije)
        listaOdgovora.adapter = listaOdgovoraAdapter

        tekstPitanja.text = pitanje.tekst
        dugmeZaustavi.setOnClickListener{
            zaustaviAnketu()
        }

            return view
        }

    private fun zaustaviAnketu() {

    }
}