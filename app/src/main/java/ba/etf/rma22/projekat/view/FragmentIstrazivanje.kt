package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.viewmodel.UpisIstrazivanjeViewModel

class FragmentIstrazivanje: Fragment() {
    private lateinit var odabirGodinaSpinner: Spinner
    private lateinit var odabirIstrazivanjaSpinner: Spinner
    private lateinit var odabirGrupaSpinner: Spinner
    private lateinit var dodajIstrazivanjeButton: Button

    private lateinit var odabirIstrazivanjaSpinnerAdapter: ArrayAdapter<Istrazivanje>
    private lateinit var odabirGrupaSpinnerAdapter: ArrayAdapter<Grupa>

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
            val poruka = "Uspješno ste upisani u grupu ${odabirGrupaSpinner.selectedItem} istraživanja ${odabirIstrazivanjaSpinner.selectedItem }!"
            Log.i("TEST", "prije upisa")
            upisIstrazivanjeViewModel.upisiIstrazivanje(
                ::prikaziPoruku,
                poruka,
                (odabirGrupaSpinner.selectedItem as Grupa).id,
                odabirGodinaSpinner.selectedItem.toString().toInt()
            )
        }
//        }else{
//            Toast.makeText(this, "Odaberi godinu, istraživanje i grupu!", Toast.LENGTH_SHORT).show()
//        }
    }
    private fun prikaziPoruku(poruka: String){
        (activity as MainActivity).prikaziPorukuUspjesanUpis(poruka)
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
            upisIstrazivanjeViewModel.popuniGrupeZaIstrazivanje(::updateGrupaSpinner, (odabirIstrazivanjaSpinner.selectedItem as Istrazivanje).id)
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

    }

    inner class OdabirGodinaSpinnerListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            //ovdje mozda cast odabrane godine u int ili salji string pa u viewModelu castaj
            upisIstrazivanjeViewModel.popuniIstrazivanjaZaGodinu(::updateIstrazivanjaSpinner, odabirGodinaSpinner.selectedItem.toString().toInt())
//            Log.i("", "Mijenjam istrazivanja!")
            //da li ovdje treba ovaj udpate grupa
            //updateGrupaSpinner()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

    }

    fun updateIstrazivanjaSpinner(istrazivanja: List<Istrazivanje>) {
        odabirIstrazivanjaSpinnerAdapter.clear()
        //dal ovo ispod moze bacit izuzetak ako nema istrazivanja na godini, izgleda da ne moze
        odabirIstrazivanjaSpinnerAdapter.addAll(istrazivanja)
        odabirIstrazivanjaSpinnerAdapter.notifyDataSetChanged()
        odabirIstrazivanjaSpinner.setSelection(0)
        if(odabirIstrazivanjaSpinner.selectedItem != null)
            upisIstrazivanjeViewModel.popuniGrupeZaIstrazivanje(::updateGrupaSpinner, (odabirIstrazivanjaSpinner.selectedItem as Istrazivanje).id)

    }

    fun updateGrupaSpinner(grupe: List<Grupa>) {
        odabirGrupaSpinnerAdapter.clear()
        odabirGrupaSpinner.setSelection(0)
//        if(!odabirIstrazivanjaSpinnerAdapter.isEmpty) {
            odabirGrupaSpinnerAdapter.addAll(grupe)
//        }
        odabirGrupaSpinnerAdapter.notifyDataSetChanged()
    }
}