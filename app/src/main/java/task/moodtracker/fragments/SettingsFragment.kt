package task.moodtracker.fragments

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import task.moodtracker.R

class SettingsFragment : Fragment() {

    private lateinit var darkModeSwitch: Switch
    private lateinit var defaultMoodSpinner: Spinner
    private lateinit var userSignatureEditText: EditText

    private val prefsName = "mood_prefs"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        darkModeSwitch = view.findViewById(R.id.darkModeSwitch)
        defaultMoodSpinner = view.findViewById(R.id.defaultMoodSpinner)
        userSignatureEditText = view.findViewById(R.id.userSignatureEditText)

        val prefs = requireContext().getSharedPreferences(prefsName, 0)

        darkModeSwitch.isChecked = prefs.getBoolean("dark_mode", false)
        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("dark_mode", isChecked).apply()
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.mood_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            defaultMoodSpinner.adapter = adapter
            val moodIndex = prefs.getInt("default_mood_index", 0)
            defaultMoodSpinner.setSelection(moodIndex)
        }

        defaultMoodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                prefs.edit().putInt("default_mood_index", pos).apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        userSignatureEditText.setText(prefs.getString("user_signature", ""))
        userSignatureEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                prefs.edit().putString("user_signature", userSignatureEditText.text.toString()).apply()
            }
        }

        return view
    }
}
