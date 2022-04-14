package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.viewmodel.AnketeViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentAnkete: Fragment() {
    private lateinit var listaAnketa: RecyclerView
    private lateinit var filterAnketa: Spinner
    private lateinit var floatingActionButton: FloatingActionButton

    private lateinit var listaAnketaAdapter: ListaAnketaAdapter
    private lateinit var filterAnketaAdapter: ArrayAdapter<String>

    private var mainActivityViewModel = AnketeViewModel()

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
        listaAnketaAdapter= ListaAnketaAdapter(mainActivityViewModel.dajAnkete())
        listaAnketa.adapter=listaAnketaAdapter

        return view
    }
    inner class FilterAnketaSpinnerListener: AdapterView.OnItemSelectedListener{
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            filtrirajAnkete()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

    }
    fun filtrirajAnkete(){
        val odabranaOpcija = filterAnketa.selectedItem as String
        listaAnketaAdapter.updateAnkete(mainActivityViewModel.filtriraj(odabranaOpcija,resources.getStringArray(R.array.filter_anketa_opcije)))
    }
}