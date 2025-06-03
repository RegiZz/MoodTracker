package task.moodtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import task.moodtracker.R
import task.moodtracker.data.FakeMoodRepository
import java.util.UUID

class MoodDetailsFragment : Fragment() {

    private val args: MoodDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mood_details, container, false)
        val entryId = UUID.fromString(args.entryId)
        val entry = FakeMoodRepository.getMoodById(entryId)

        val dateText: TextView = view.findViewById(R.id.dateText)
        val moodText: TextView = view.findViewById(R.id.moodText)
        val noteText: TextView = view.findViewById(R.id.noteText)
        val categoryText: TextView = view.findViewById(R.id.categoryText)
        val sleptText: TextView = view.findViewById(R.id.sleptText)
        val activeText: TextView = view.findViewById(R.id.activeText)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
        val importantText: TextView = view.findViewById(R.id.importantText)
        val deleteButton: Button = view.findViewById(R.id.deleteButton)
        val shareButton: Button = view.findViewById(R.id.shareButton)

        if (entry == null) {
            Toast.makeText(context, "Wpis nie znaleziony", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
            return view
        }

        dateText.text = entry.formattedDate()
        moodText.text = entry.mood
        noteText.text = entry.note
        categoryText.text = entry.category
        sleptText.text = if (entry.sleptWell) "Dobrze spałem" else "Nie spałem dobrze"
        activeText.text = if (entry.wasActive) "Byłem aktywny" else "Nie byłem aktywny"
        ratingBar.rating = entry.rating
        importantText.text = if (entry.isImportant) "Ważny dzień" else "Nie ważny"

        deleteButton.setOnClickListener {
            FakeMoodRepository.deleteMood(entry.id)
            findNavController().popBackStack()
        }

        shareButton.setOnClickListener {
            Toast.makeText(context, "Udostępniono", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
