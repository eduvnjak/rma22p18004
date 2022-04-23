package ba.etf.rma22.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private var izvrsenUpis = false
    private var anketaZaustavljena = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragments =
            mutableListOf(
                FragmentAnkete(),
                FragmentIstrazivanje(),
            )
        viewPager = findViewById(R.id.fragment_view_pager)
        viewPager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments, lifecycle)
        viewPager.adapter = viewPagerAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(position == 0 && (izvrsenUpis || anketaZaustavljena)){
                    izvrsenUpis = false
                    anketaZaustavljena = false
                    viewPagerAdapter.refreshFragment(1,FragmentIstrazivanje())                }
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

    fun pokreniIspunjavanjeAnkete(anketa: Anketa, pitanjaZaAnketu: List<Pitanje>) {
        Log.i("KLIKNO SI NA", anketa.naziv+" "+anketa.nazivIstrazivanja+" ima "+pitanjaZaAnketu.size)
        //broj pitanja
        viewPagerAdapter.remove(0)
        viewPagerAdapter.remove(0)
        val brojPitanja = pitanjaZaAnketu.size
        for(i in 0 until brojPitanja){
            viewPagerAdapter.add(i,FragmentPitanje.newInstance(pitanjaZaAnketu[i], anketa))
        }
        viewPagerAdapter.add(brojPitanja,FragmentPredaj.newInstance(anketa))

        //popuni podacima
    }
    fun prikaziPorukuZaustaviAnketu(poruka: String){
        val fragmentPoruka = FragmentPoruka.newInstance(poruka)
        val fragmentAnkete = FragmentAnkete.newInstance()

        val brojFragmenata = viewPagerAdapter.itemCount

        viewPagerAdapter.refreshFragment(0,fragmentAnkete)
        viewPagerAdapter.refreshFragment(1,fragmentPoruka)
        viewPager.setCurrentItem(1,true)

        for(i in brojFragmenata-1 downTo  2){
            viewPagerAdapter.remove(i)
        }
        anketaZaustavljena = true
    }
}