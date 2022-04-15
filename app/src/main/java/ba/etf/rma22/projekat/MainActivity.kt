package ba.etf.rma22.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.view.FragmentAnkete
import ba.etf.rma22.projekat.view.FragmentIstrazivanje
import ba.etf.rma22.projekat.view.FragmentPoruka

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private var izvrsenUpis = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragments =
            mutableListOf(
                FragmentAnkete(),
                FragmentIstrazivanje(),
            )
        viewPager = findViewById(R.id.fragment_view_pager)
        viewPager.offscreenPageLimit = 1
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments, lifecycle)
        viewPager.adapter = viewPagerAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(position == 0 && izvrsenUpis){
                    izvrsenUpis = false
                    viewPagerAdapter.refreshFragment(1,FragmentIstrazivanje())                }
            }
        })
    }
    fun prikaziPoruku(grupa: String, istrazivanje: String){
        val fragmentPoruka = FragmentPoruka()

        val bundle = Bundle()
        bundle.putString("grupa",grupa)
        bundle.putString("istrazivanje",istrazivanje)
        fragmentPoruka.arguments = bundle

        viewPagerAdapter.refreshFragment(1,fragmentPoruka)
        viewPagerAdapter.refreshFragment(0,FragmentAnkete())

        izvrsenUpis = true
    }

    fun pokreniIspunjavanjeAnkete(nazivAnkete: String, nazivIstrazivanja: String) {
        Log.i("KLIKNO SI NA", nazivAnkete+" "+nazivIstrazivanja)
    }
}