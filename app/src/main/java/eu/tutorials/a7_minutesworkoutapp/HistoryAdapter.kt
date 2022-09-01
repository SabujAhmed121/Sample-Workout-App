package eu.tutorials.a7_minutesworkoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.tutorials.a7_minutesworkoutapp.databinding.ItemHistoryRowBinding

class historyAdapter(private val items : ArrayList<String>):
    RecyclerView.Adapter<historyAdapter.historyHolder>() {

    inner class historyHolder(val bindingHis: ItemHistoryRowBinding) :
        RecyclerView.ViewHolder(bindingHis.root) {
        val llHistoryItemBack = bindingHis.llHistoryItemMain
        val tvPosition = bindingHis.tvPosition
        val tvItem = bindingHis.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): historyHolder {
        return historyHolder(
            ItemHistoryRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: historyHolder, position: Int) {

        val date: String = items[position]
        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date

        if (position % 2 == 0) {
            holder.llHistoryItemBack.setBackgroundColor(Color.parseColor("#EBEBEB"))
        } else {
            holder.llHistoryItemBack.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}