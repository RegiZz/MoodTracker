package task.moodtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import task.moodtracker.R
import task.moodtracker.data.FakeMoodRepository
import task.moodtracker.data.MoodEntry

class MoodEntryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mood_entry, container, false)

        val noteEditText: EditText = view.findViewById(R.id.noteEditText)
        val moodGroup: RadioGroup = view.findViewById(R.id.moodGroup)
        val categorySpinner: Spinner = view.findViewById(R.id.categorySpinner)
        val sleptCheckBox: CheckBox = view.findViewById(R.id.sleptCheckBox)
        val activeCheckBox: CheckBox = view.findViewById(R.id.activeCheckBox)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
        val importantSwitch: Switch = view.findViewById(R.id.importantSwitch)
        val saveButton: Button = view.findViewById(R.id.saveButton)
        val settingsButton: ImageButton = view.findViewById(R.id.settingsButton)
        val historyButton: ImageButton = view.findViewById(R.id.historyButton)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.categories,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter
        }

        settingsButton.setOnClickListener{
            findNavController().navigate(R.id.action_moodEntryFragment_to_settingsFragment)
        }

        historyButton.setOnClickListener{
            findNavController().navigate(R.id.action_moodEntryFragment_to_moodHistoryFragment)
        }

        saveButton.setOnClickListener {
            val selectedMoodId = moodGroup.checkedRadioButtonId
            if (selectedMoodId == -1) {
                Toast.makeText(requireContext(), "Wybierz nastrój", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val mood = view.findViewById<RadioButton>(selectedMoodId).text.toString()

            val entry = MoodEntry(
                mood = mood,
                note = noteEditText.text.toString(),
                category = categorySpinner.selectedItem.toString(),
                sleptWell = sleptCheckBox.isChecked,
                wasActive = activeCheckBox.isChecked,
                rating = ratingBar.rating,
                isImportant = importantSwitch.isChecked
            )

            FakeMoodRepository.addMood(entry)
            findNavController().navigate(R.id.action_moodEntryFragment_to_moodHistoryFragment)
        }

        return view
    }
}
