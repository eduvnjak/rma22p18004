package ba.etf.rma22.projekat.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.viewmodel.UpisIstrazivanjeViewModel

class FragmentIstrazivanje: Fragment() {
    private lateinit var odabirGodinaSpinner: Spinner
    private lateinit var odabirIstrazivanjaSpinner: Spinner
    private lateinit var odabirGrupaSpinner: Spinner
    private lateinit var dodajIstrazivanjeButton: Button

    private lateinit var odabirIstrazivanjaSpinnerAdapter: ArrayAdapter<String>
    private lateinit var odabirGrupaSpinnerAdapter: ArrayAdapter<String>

    private var upisIstrazivanjeViewModel = UpisIstrazivanjeViewModel()
    companion object{
        fun newInstance(): FragmentIstrazivanje = FragmentIstrazivanje()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_istrazivanje,container,false)

        odabirGodinaSpinner = view.findViewById(R.id.odabirGodina)
        odabirIstrazivanjaSpinner = view.findViewById(R.id.odabirIstrazivanja)
        odabirGrupaSpinner = view.findViewById(R.id.odabirGrupa)
        dodajIstrazivanjeButton = view.findViewById(R.id.dodajIstrazivanjeDugme)

        ArrayAdapter.createFromResource(requireActivity(),R.array.spinner_godina_opcije,android.R.layout.simple_spinner_item).also {
                adapter ->  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            odabirGodinaSpinner.adapter = adapter}
        odabirGodinaSpinner.onItemSelectedListener = OdabirGodinaSpinnerListener()
        odabirGodinaSpinner.setSelection((odabirGodinaSpinner.adapter as ArrayAdapter<String>).getPosition(upisIstrazivanjeViewModel.dajPosljednjuOdabranuGodinu()))

        odabirIstrazivanjaSpinnerAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item)
        odabirIstrazivanjaSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        odabirIstrazivanjaSpinner.adapter = odabirIstrazivanjaSpinnerAdapter
        odabirIstrazivanjaSpinner.onItemSelectedListener = OdabirIstrazivanjaSpinnerListener()

        odabirGrupaSpinnerAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item)
        odabirGrupaSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        odabirGrupaSpinner.adapter = odabirGrupaSpinnerAdapter
        odabirGrupaSpinner.onItemSelectedListener = OdabirGrupaSpinnerListener()

        dodajIstrazivanjeButton.setOnClickListener{
            dodajIstrazivanjeButtonAction()
        }
        return view
    }
    private fun dodajIstrazivanjeButtonAction() {

        if(odabirIstrazivanjaSpinner.selectedItem != null && odabirGodinaSpinner.selectedItem != null && odabirGrupaSpinner.selectedItem != null) {
            upisIstrazivanjeViewModel.upisiIstrazivanje(
                odabirIstrazivanjaSpinner.selectedItem as String,
                odabirGrupaSpinner.selectedItem as String,
                odabirGodinaSpinner.selectedItem as String
            )
            (activity as MainActivity).prikaziPoruku(odabirGrupaSpinner.selectedItem as String, odabirIstrazivanjaSpinner.selectedItem as String)
        }
//        }else{
//            Toast.makeText(this, "Odaberi godinu, istraživanje i grupu!", Toast.LENGTH_SHORT).show()
//        }
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
//            Log.i("", "Mijenjam istrazivanja!")
            updateGrupaSpinner()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

    }

    fun updateIstrazivanjaSpinner() {
        odabirIstrazivanjaSpinnerAdapter.clear()
        odabirIstrazivanjaSpinner.setSelection(0)
        odabirIstrazivanjaSpinnerAdapter.addAll(
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
}