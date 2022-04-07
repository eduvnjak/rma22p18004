package ba.unsa.etf.rma.rma22p18004

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridLayout
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var listaAnketa: RecyclerView
    private lateinit var filterAnketa: Spinner

    private lateinit var listaAnketaAdapter: ListaAnketaAdapter
    private lateinit var filterAnketaAdapter: ArrayAdapter<String>

    private var mainActivityViewModel = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaAnketa = findViewById(R.id.listaAnketa)
        filterAnketa = findViewById(R.id.filterAnketa)

        ArrayAdapter.createFromResource(this,R.array.filter_anketa_opcije,android.R.layout.simple_spinner_item).also {
                adapter ->  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            filterAnketa.adapter = adapter}
        filterAnketa.onItemSelectedListener = filterAnketaSpinnerListener()

        listaAnketa.layoutManager=GridLayoutManager(this, 2)
        listaAnketaAdapter= ListaAnketaAdapter(mainActivityViewModel.dajAnkete())
        listaAnketa.adapter=listaAnketaAdapter
    }

    inner class filterAnketaSpinnerListener: AdapterView.OnItemSelectedListener{
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            filtrirajAnkete()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }
    fun filtrirajAnkete(){

    }
}