package task.moodtracker

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var moodStorage: MoodStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moodStorage = MoodStorage(this)

        val moodGroup = findViewById<RadioGroup>(R.id.moodGroup)
        val noteInput = findViewById<EditText>(R.id.noteInput)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val historyButton = findViewById<Button>(R.id.historyButton)

        saveButton.setOnClickListener {
            val selectedId = moodGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Wybierz nastrój", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val mood = findViewById<RadioButton>(selectedId).text.toString()
            val note = noteInput.text.toString()
            val date = LocalDate.now().toString()

            moodStorage.saveMood(Mood(date, mood, note))
            Toast.makeText(this, "Zapisano!", Toast.LENGTH_SHORT).show()
            noteInput.text.clear()
            moodGroup.clearCheck()
        }

        historyButton.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}