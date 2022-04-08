package ba.unsa.etf.rma.rma22p18004.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.rma.rma22p18004.R
import ba.unsa.etf.rma.rma22p18004.models.Anketa
import java.text.SimpleDateFormat

class ListaAnketaAdapter(private var dataSet: List<Anketa>):
    RecyclerView.Adapter<ListaAnketaAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewStanjeAnkete: ImageView = view.findViewById(R.id.imageView_stanje_ankete)
        val progressBarProgresZavrsetka: ProgressBar = view.findViewById(R.id.progresZavrsetka)
        val textViewIstrazivanjeNaziv: TextView = view.findViewById(R.id.textView_istrazivanje_naziv)
        val textViewAnketaNaziv: TextView = view.findViewById(R.id.textView_anketa_naziv)
        val textViewAnketaDatum: TextView = view.findViewById(R.id.textView_anketa_datum)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_anketa_list_element, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewAnketaNaziv.text = dataSet[position].naziv
        holder.textViewIstrazivanjeNaziv.text = dataSet[position].nazivIstrazivanja
        //progress
        holder.progressBarProgresZavrsetka.progress=(dataSet[position].progres*100).toInt()
        //datum i status
        val context =holder.imageViewStanjeAnkete.context
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        when(dataSet[position].dajStatusAnkete()){
            1->{
                holder.textViewAnketaDatum.text=context.getString(R.string.anketa_uradjena, simpleDateFormat.format(dataSet[position].dajDatumZaListu()))
                holder.imageViewStanjeAnkete.setImageResource(context.resources.getIdentifier("plava","drawable",context.packageName))
            }
            2->{
                holder.textViewAnketaDatum.text=context.getString(R.string.vrijeme_zatvaranja, simpleDateFormat.format(dataSet[position].dajDatumZaListu()))
                holder.imageViewStanjeAnkete.setImageResource(context.resources.getIdentifier("zelena","drawable",context.packageName))
            }
            3->{
                holder.textViewAnketaDatum.text=context.getString(R.string.vrijeme_aktiviranja, simpleDateFormat.format(dataSet[position].dajDatumZaListu()))
                holder.imageViewStanjeAnkete.setImageResource(context.resources.getIdentifier("zuta","drawable",context.packageName))
            }
            4->{
                holder.textViewAnketaDatum.text=context.getString(R.string.anketa_zatvorena, simpleDateFormat.format(dataSet[position].dajDatumZaListu()))
                holder.imageViewStanjeAnkete.setImageResource(context.resources.getIdentifier("crvena","drawable",context.packageName))
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateAnkete(noveAnkete: List<Anketa>) {
        dataSet=noveAnkete
        notifyDataSetChanged()
    }


}
