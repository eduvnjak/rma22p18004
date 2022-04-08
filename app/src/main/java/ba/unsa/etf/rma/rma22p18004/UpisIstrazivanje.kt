package ba.unsa.etf.rma.rma22p18004

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ba.unsa.etf.rma.rma22p18004.viewmodel.UpisIstrazivanjeViewModel

class UpisIstrazivanje: AppCompatActivity() {

    private lateinit var odabirGodinaSpinner: Spinner
    private lateinit var odabirIstrazivanjaSpinner: Spinner
    private lateinit var odabirGrupaSpinner: Spinner
    private lateinit var dodajIstrazivanjeButton: Button

    //private lateinit var odabirGodinaSpinnerAdapter: ArrayAdapter<String>
    private lateinit var odabirIstrazivanjaSpinnerAdapter: ArrayAdapter<String>
    private lateinit var odabirGrupaSpinnerAdapter: ArrayAdapter<String>

    private var upisIstrazivanjeViewModel = UpisIstrazivanjeViewModel()

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

        dodajIstrazivanjeButton.setOnClickListener{
            dodajIstrazivanjeButtonAction()
        }
    }

    private fun dodajIstrazivanjeButtonAction() {
        if(odabirIstrazivanjaSpinner.selectedItem != null && odabirGodinaSpinner.selectedItem != null && odabirGrupaSpinner.selectedItem != null) {
            upisIstrazivanjeViewModel.upisiIstrazivanje(odabirIstrazivanjaSpinner.selectedItem as String, odabirGrupaSpinner.selectedItem as String, odabirGodinaSpinner.selectedItem as String)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this, "Odaberi godinu, istraživanje i grupu!", Toast.LENGTH_SHORT).show()
        }
    }

    inner class OdabirGrupaSpinnerListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            dodajIstrazivanjeButton.isEnabled=true
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            dodajIstrazivanjeButton.isEnabled=false
        }

    }

    inner class OdabirIstrazivanjaSpinnerListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            updateGrupaSpinner()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

    }

    inner class OdabirGodinaSpinnerListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            updateIstrazivanjaSpinner()
            updateGrupaSpinner()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

    }

    fun updateIstrazivanjaSpinner() {
        odabirIstrazivanjaSpinnerAdapter.clear()
        odabirIstrazivanjaSpinner.setSelection(0)
        odabirGrupaSpinnerAdapter.addAll(
            upisIstrazivanjeViewModel.dajIstrazivanja(odabirGodinaSpinner.selectedItem as String)
        )
        odabirIstrazivanjaSpinnerAdapter.notifyDataSetChanged()
    }

    fun updateGrupaSpinner() {
        odabirGrupaSpinnerAdapter.clear()
        odabirGrupaSpinner.setSelection(0)
        if(!odabirIstrazivanjaSpinnerAdapter.isEmpty) {
            odabirGrupaSpinnerAdapter.addAll(
                upisIstrazivanjeViewModel.dajGrupe(odabirIstrazivanjaSpinner.selectedItem as String)
            )
        }
        odabirGrupaSpinnerAdapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        // da li je potrebno
        finish()
    }
}