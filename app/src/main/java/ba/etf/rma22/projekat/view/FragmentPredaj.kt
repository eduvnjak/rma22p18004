package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.viewmodel.AnketeViewModel

class FragmentPredaj: Fragment() {
    private lateinit var progresTekst: TextView
    private lateinit var dugmePredaj: Button

    private lateinit var nazivAnkete: String
    private lateinit var nazivIstrazivanja: String

    private var anketeViewModel = AnketeViewModel()
    
    companion object{
        fun newInstance(nazivAnkete: String, nazivIstrazivanja: String): FragmentPredaj {
            val args = Bundle()
            args.putString("anketaNaziv", nazivAnkete)
            args.putString("istrazivanjeNaziv", nazivIstrazivanja)
            val fragment = FragmentPredaj()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_predaj, container,false)

        progresTekst = view.findViewById(R.id.progresTekst)
        dugmePredaj = view.findViewById(R.id.dugmePredaj)

        //zasto ovdje tostring treba
        nazivAnkete = arguments?.getString("anketaNaziv","").toString()
        nazivIstrazivanja = arguments?.getString("istrazivanjeNaziv","").toString()

        progresTekst.text = anketeViewModel.dajProgres(nazivAnkete, nazivIstrazivanja)

        dugmePredaj.setOnClickListener{
            predajAnketu()
        }

        return view
    }

    private fun predajAnketu() {

    }
    private fun updateProgress() {
        progresTekst.text = anketeViewModel.dajProgres(nazivAnkete, nazivIstrazivanja)
    }
}