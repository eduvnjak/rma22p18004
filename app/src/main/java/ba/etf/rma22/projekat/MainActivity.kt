package ba.etf.rma22.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.view.*
import ba.etf.rma22.projekat.viewmodel.AnketeViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private var izvrsenUpis = false
    private var anketaZaustavljena = false

    private var pitanjeAnketaViewModel = PitanjeAnketaViewModel()
    private var anketeViewModel = AnketeViewModel()
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragments =
            mutableListOf(
                FragmentAnkete(),
                FragmentIstrazivanje(),
            )
        viewPager = findViewById(R.id.pager)
        viewPager.offscreenPageLimit = 10
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments, lifecycle)
        viewPager.adapter = viewPagerAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(position == 0 && (izvrsenUpis || anketaZaustavljena)){
                    izvrsenUpis = false
                    anketaZaustavljena = false
                    viewPagerAdapter.refreshFragment(1,FragmentIstrazivanje())
                }
            }
        })
    }
    fun prikaziPorukuUspjesanUpis(poruka: String){
        val fragmentPoruka = FragmentPoruka.newInstance(poruka)


        //da li ovaj refresh ispod ili samo pozvati neku metodu osvjezi ankete u fragment poruka
        viewPagerAdapter.refreshFragment(1,fragmentPoruka)
        viewPagerAdapter.refreshFragment(0,FragmentAnkete())

        izvrsenUpis = true
    }
    fun pokreniIspunjavanjeAnkete(anketa: Anketa) {
//        scope.launch {
//            val pokusaj = TakeAnketaRepository.dajPokusajZaAnketu(anketa.id)
//            Toast.makeText(this@MainActivity, pokusaj?.id.toString() ?: "-1", Toast.LENGTH_SHORT).show()
//        }
        pitanjeAnketaViewModel.dajPitanjaZaAnketuIPokusaj(anketa, ::prikaziPitanjaIspunjavanje)
    }

    fun prikaziPitanjaIspunjavanje(anketa: Anketa, pitanjaZaAnketu: List<Pitanje>, odgovoriDosadasnji: List<Odgovor>) {
//        Log.i("KLIKNO SI NA", anketa.naziv+" "+anketa.nazivIstrazivanja+" ima "+pitanjaZaAnketu.size)
        viewPagerAdapter.remove(0)
        viewPagerAdapter.remove(0)
        val brojPitanja = pitanjaZaAnketu.size
        for(i in 0 until brojPitanja) {
            val indeksOdgovora = odgovoriDosadasnji.find { odgovor -> odgovor.pitanjeId == pitanjaZaAnketu[i].id }?.odgovoreno
            Log.i("TEST", pitanjaZaAnketu[i].tekstPitanja + " " + indeksOdgovora +" " + odgovoriDosadasnji.size)
            viewPagerAdapter.add(i,FragmentPitanje.newInstance(pitanjaZaAnketu[i], indeksOdgovora, false))
        }
        viewPagerAdapter.add(brojPitanja,FragmentPredaj.newInstance(anketa, false))
        azurirajProgresUFragmentu()
    }

    fun zaustaviAnketu(){
        val fragmentAnkete = FragmentAnkete.newInstance()
        val fragmentIstrazivanje = FragmentIstrazivanje.newInstance()

        viewPagerAdapter.ocistiSve()
        viewPagerAdapter.add(0,fragmentAnkete)
        viewPagerAdapter.add(1,fragmentIstrazivanje)
        viewPager.setCurrentItem(0,false)
    }

    fun azurirajProgresUFragmentu() {
////        Log.i("TAGTAG","azuriram")
        val ukupanBrojPitanja = viewPagerAdapter.itemCount-1
        var ukupanBrojOdgovorenih = 0
        for (i in 0 until ukupanBrojPitanja) {
            if((viewPagerAdapter.dajFragment(i) as FragmentPitanje).odgovorIndeks != null){
                ukupanBrojOdgovorenih++
            }
        }
        val progres = anketeViewModel.dajZaokruzenProgres(ukupanBrojOdgovorenih, ukupanBrojPitanja)
        (viewPagerAdapter.dajFragment(viewPagerAdapter.itemCount-1) as FragmentPredaj).updateProgress(progres)
    }

    fun pokreniPregledAnkete(anketa: Anketa) {
        pitanjeAnketaViewModel.dajPitanjaZaAnketuIPokusaj(anketa, ::prikaziPitanjaPregled)
    }

    fun prikaziPitanjaPregled(anketa: Anketa, pitanjaZaAnketu: List<Pitanje>, odgovoriDosadasnji: List<Odgovor>) {
        viewPagerAdapter.remove(0)
        viewPagerAdapter.remove(0)
        val brojPitanja = pitanjaZaAnketu.size
        for(i in 0 until brojPitanja){
            val indeksOdgovora = odgovoriDosadasnji.find { odgovor -> odgovor.pitanjeId == pitanjaZaAnketu[i].id }?.odgovoreno
            viewPagerAdapter.add(i,FragmentPitanje.newInstance(pitanjaZaAnketu[i], indeksOdgovora, true))
        }
        azurirajProgresUFragmentu()
    }

    fun predajAnketu(poruka: String, anketaId: Int) {
        //posalji odgovore
        val mapaPitanjeOdgovor = mutableMapOf<Pitanje, Int?>()
        for (i in 0 until viewPagerAdapter.itemCount-1) {
            mapaPitanjeOdgovor[(viewPagerAdapter.dajFragment(i) as FragmentPitanje).pitanje] = (viewPagerAdapter.dajFragment(i) as FragmentPitanje).odgovorIndeks
        }
        pitanjeAnketaViewModel.postaviOdgovore(mapaPitanjeOdgovor, anketaId, ::anketaPredanaPrikaziPoruku, poruka)
        //pa ocisti i pozovi ovu dole

    }
    fun anketaPredanaPrikaziPoruku(poruka: String) {
        val fragmentPoruka = FragmentPoruka.newInstance(poruka)
        val fragmentAnkete = FragmentAnkete.newInstance()

        viewPagerAdapter.ocistiSve()
        viewPagerAdapter.add(0,fragmentAnkete)
        viewPagerAdapter.add(1,fragmentPoruka)
        viewPager.setCurrentItem(1,false)


        anketaZaustavljena = true
    }
}