package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R

class FragmentPoruka: Fragment() {
    private lateinit var tvPoruka: TextView
    companion object{
        fun newInstance(): FragmentPoruka = FragmentPoruka()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_poruka, container, false)
        tvPoruka = view.findViewById(R.id.tvPoruka)

        val istrazivanje = requireArguments().get("istrazivanje")
        val grupa = requireArguments().get("grupa")

        val poruka = "Uspješno ste upisani u grupu ${grupa} istraživanja ${istrazivanje}!"
        tvPoruka.text = poruka
        return view
    }

}