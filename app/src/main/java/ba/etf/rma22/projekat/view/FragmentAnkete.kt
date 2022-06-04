package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.viewmodel.AnketeViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel

class FragmentAnkete: Fragment() {
    private lateinit var listaAnketa: RecyclerView
    private lateinit var filterAnketa: Spinner

    private lateinit var listaAnketaAdapter: ListaAnketaAdapter
    private lateinit var filterAnketaAdapter: ArrayAdapter<String>

    private var anketeViewModel = AnketeViewModel()
    private var pitanjeAnketaViewModel = PitanjeAnketaViewModel()

    companion object{
        fun newInstance(): FragmentAnkete = FragmentAnkete()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ankete,container,false)

        listaAnketa = view.findViewById(R.id.listaAnketa)
        filterAnketa = view.findViewById(R.id.filterAnketa)

        ArrayAdapter.createFromResource(requireActivity(),R.array.filter_anketa_opcije,android.R.layout.simple_spinner_item).also {
                adapter ->  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            filterAnketa.adapter = adapter}
        filterAnketa.onItemSelectedListener = FilterAnketaSpinnerListener()

        listaAnketa.layoutManager= GridLayoutManager(activity, 2)
        //listaAnketaAdapter= ListaAnketaAdapter(anketeViewModel.dajAnkete()) { anketa ->  ispuniAnketu(anketa)}
        listaAnketaAdapter= ListaAnketaAdapter(emptyList()) { anketa ->  ispuniAnketu(anketa)}
        listaAnketa.adapter=listaAnketaAdapter

        return view
    }

    private fun ispuniAnketu(anketa: Anketa) {
        //provjeri da li se anketa moze ispuniti
//        if(anketa.dajStatusAnkete() == 3){
//            Toast.makeText(requireContext(),"Anketa još nije aktivna", Toast.LENGTH_SHORT).show()
//            return
//        }
//        if(anketa.dajStatusAnkete() == 2){
//            (activity as MainActivity).pokreniIspunjavanjeAnkete(anketa, pitanjeAnketaViewModel.dajPitanjaZaAnketu(anketa))
//        }else{
//            (activity as MainActivity).pokreniPregledAnkete(anketa, pitanjeAnketaViewModel.dajPitanjaZaAnketu(anketa))
//        }
    }

    inner class FilterAnketaSpinnerListener: AdapterView.OnItemSelectedListener{
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            val toast = Toast.makeText(context,"Dohvacanje anketa",Toast.LENGTH_SHORT)
            toast.show()
            filtrirajAnkete()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

    }
    fun filtrirajAnkete(){
        val odabranaOpcija = filterAnketa.selectedItem as String
        listaAnketaAdapter.updateAnkete(anketeViewModel.filtriraj(::prikaziAnkete,odabranaOpcija,resources.getStringArray(R.array.filter_anketa_opcije)))
    }
    private fun prikaziAnkete(noveAnkete: List<Anketa>) {
        listaAnketaAdapter.updateAnkete(noveAnkete)
    }
}