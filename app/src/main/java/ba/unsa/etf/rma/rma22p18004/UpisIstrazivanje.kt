package ba.unsa.etf.rma.rma22p18004

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class UpisIstrazivanje: AppCompatActivity() {

    private lateinit var odabirGodinaSpinner: Spinner
    private lateinit var odabirIstrazivanjaSpinner: Spinner
    private lateinit var odabirGrupaSpinner: Spinner
    private lateinit var dodajIstrazivanjeButton: Button

    private lateinit var odabirGodinaSpinnerAdapter: ArrayAdapter<String>
    private lateinit var odabirIstrazivanjaSpinnerAdapter: ArrayAdapter<String>
    private lateinit var odabirGrupaSpinnerAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upis_istrazivanja)

        odabirGodinaSpinner = findViewById(R.id.odabirGodina)
        odabirIstrazivanjaSpinner = findViewById(R.id.odabirIstrazivanja)
        odabirGrupaSpinner = findViewById(R.id.odabirGrupa)
        dodajIstrazivanjeButton = findViewById(R.id.dodajIstrazivanjeDugme)

        ArrayAdapter.createFromResource(this,R.array.spinner_godina_opcije,android.R.layout.simple_spinner_item).also {
                adapter ->  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            odabirGodinaSpinner.adapter = adapter}
        odabirGodinaSpinner.onItemSelectedListener = OdabirGodinaSpinnerListener()

        odabirIstrazivanjaSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item)
        odabirIstrazivanjaSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        odabirIstrazivanjaSpinner.adapter = odabirIstrazivanjaSpinnerAdapter
        odabirIstrazivanjaSpinner.onItemSelectedListener = OdabirIstrazivanjaSpinnerListener()

        odabirGrupaSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        odabirGrupaSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        odabirGrupaSpinner.adapter = odabirGrupaSpinnerAdapter
        odabirGrupaSpinner.onItemSelectedListener = OdabirGrupaSpinnerListener()
    }

    class OdabirGrupaSpinnerListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            TODO("Not yet implemented")
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }

    class OdabirIstrazivanjaSpinnerListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            TODO("Not yet implemented")
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }

    inner class OdabirGodinaSpinnerListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            TODO("Not yet implemented")
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }
}