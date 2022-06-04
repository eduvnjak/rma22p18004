package ba.etf.rma22.projekat.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import java.text.SimpleDateFormat
import java.util.*

class ListaAnketaAdapter(private var dataSet: List<Anketa>,
                        private val onItemClicked: (anketa: Anketa) -> Unit
): RecyclerView.Adapter<ListaAnketaAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewStanjeAnkete: ImageView = view.findViewById(R.id.imageView_stanje_ankete)
        val progressBarProgresZavrsetka: ProgressBar = view.findViewById(R.id.progresZavrsetka)
        val textViewIstrazivanjeNaziv: TextView = view.findViewById(R.id.textView_istrazivanje_naziv)
        val textViewAnketaNaziv: TextView = view.findViewById(R.id.textView_anketa_naziv)
        val textViewAnketaDatum: TextView = view.findViewById(R.id.textView_anketa_datum)
        val textViewAnketaDatumText: TextView = view.findViewById(R.id.textView_anketa_datum_text)

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
        holder.progressBarProgresZavrsetka.progress=(dataSet[position].progres ?: 0)
        //datum i status
        val context = holder.imageViewStanjeAnkete.context
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")

        val trenutniDatum = Calendar.getInstance().time
        var statusBoja = "zelena"

        if(dataSet[position].predana) {
            statusBoja = "plava"
            //todo dobavi datum
            holder.textViewAnketaDatumText.text = ""
        } else if (trenutniDatum < dataSet[position].datumPocetak) {
            statusBoja = "zuta"
            holder.textViewAnketaDatumText.text = context.getString(R.string.vrijeme_aktiviranja)
            holder.textViewAnketaDatum.text = simpleDateFormat.format(dataSet[position].datumPocetak)
        } else if (dataSet[position].datumKraj != null && trenutniDatum > dataSet[position].datumKraj){
            statusBoja = "crvena"
            holder.textViewAnketaDatumText.text = context.getString(R.string.anketa_zatvorena)
            holder.textViewAnketaDatum.text = simpleDateFormat.format(dataSet[position].datumKraj!!)
        } else{
            statusBoja = "zelena"
            holder.textViewAnketaDatumText.text = context.getString(R.string.vrijeme_zatvaranja)
            if (dataSet[position].datumKraj != null){
                holder.textViewAnketaDatum.text = simpleDateFormat.format(dataSet[position].datumKraj)
            } else {
                holder.textViewAnketaDatum.text = "Nikada"
            }
        }
        holder.imageViewStanjeAnkete.setImageResource(context.resources.getIdentifier(statusBoja,"drawable",context.packageName))

//        when(dataSet[position].dajStatusAnkete()){
//            1->{
//                holder.textViewAnketaDatumText.text=context.getString(R.string.anketa_uradjena)
//                holder.textViewAnketaDatum.text=simpleDateFormat.format(dataSet[position].dajDatumZaListu())
//                holder.imageViewStanjeAnkete.setImageResource(context.resources.getIdentifier("plava","drawable",context.packageName))
//            }
//            2->{
//                holder.textViewAnketaDatumText.text=context.getString(R.string.vrijeme_zatvaranja)
//                holder.textViewAnketaDatum.text=simpleDateFormat.format(dataSet[position].dajDatumZaListu())
//                holder.imageViewStanjeAnkete.setImageResource(context.resources.getIdentifier("zelena","drawable",context.packageName))
//            }
//            3->{
//                holder.textViewAnketaDatumText.text=context.getString(R.string.vrijeme_aktiviranja)
//                holder.textViewAnketaDatum.text=simpleDateFormat.format(dataSet[position].dajDatumZaListu())
//                holder.imageViewStanjeAnkete.setImageResource(context.resources.getIdentifier("zuta","drawable",context.packageName))
//            }
//            4->{
//                holder.textViewAnketaDatumText.text=context.getString(R.string.anketa_zatvorena)
//                holder.textViewAnketaDatum.text=simpleDateFormat.format(dataSet[position].dajDatumZaListu())
//                holder.imageViewStanjeAnkete.setImageResource(context.resources.getIdentifier("crvena","drawable",context.packageName))
//            }
//        }

        //TODO("postavi razlog postavljanja listenera")
        holder.itemView.setOnClickListener{onItemClicked(dataSet[position])}
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateAnkete(noveAnkete: List<Anketa>) {
        dataSet=noveAnkete
        notifyDataSetChanged()
    }


}
