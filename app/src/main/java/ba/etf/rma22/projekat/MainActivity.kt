package ba.etf.rma22.projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.view.ListaAnketaAdapter
import ba.etf.rma22.projekat.viewmodel.MainActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var listaAnketa: RecyclerView
    private lateinit var filterAnketa: Spinner
    private lateinit var floatingActionButton: FloatingActionButton

    private lateinit var listaAnketaAdapter: ListaAnketaAdapter
    private lateinit var filterAnketaAdapter: ArrayAdapter<String>

    private var mainActivityViewModel = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaAnketa = findViewById(R.id.listaAnketa)
        filterAnketa = findViewById(R.id.filterAnketa)
        floatingActionButton = findViewById(R.id.floatingActionButton)

        ArrayAdapter.createFromResource(this,R.array.filter_anketa_opcije,android.R.layout.simple_spinner_item).also {
                adapter ->  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            filterAnketa.adapter = adapter}
        filterAnketa.onItemSelectedListener = FilterAnketaSpinnerListener()

        listaAnketa.layoutManager=GridLayoutManager(this, 2)
        listaAnketaAdapter= ListaAnketaAdapter(mainActivityViewModel.dajAnkete())
        listaAnketa.adapter=listaAnketaAdapter

        floatingActionButton.setOnClickListener {
            pokreniUpisIstrazivanjeActivity()
        }
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
    fun pokreniUpisIstrazivanjeActivity() {
        val intent = Intent(this, UpisIstrazivanje::class.java)
        startActivity(intent)
        finish()
    }
}