package task.moodtracker.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import task.moodtracker.R
import task.moodtracker.data.MoodEntry

class MoodAdapter(
    private val items: List<MoodEntry>,
    private val onItemClick: (MoodEntry) -> Unit
) : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mood, parent, false)
        return MoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        val entry = items[position]
        holder.bind(entry)
        holder.itemView.setOnClickListener { onItemClick(entry) }
    }

    override fun getItemCount(): Int = items.size

    class MoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moodText: TextView = itemView.findViewById(R.id.moodText)
        private val emoji: ImageView = itemView.findViewById(R.id.moodIcon)
        fun bind(entry: MoodEntry) {
            moodText.text = "${entry.formattedDate()} - ${entry.mood} - ${entry.note.take(30)}"
            when(entry.mood){
                "Wesoły" -> emoji.setImageResource(R.drawable.ic_mood_happy)
                "Przeciętny" -> emoji.setImageResource(R.drawable.ic_mood_neutral)
                "Smutny" -> emoji.setImageResource(R.drawable.ic_mood_sad)
            }
        }
    }
}
