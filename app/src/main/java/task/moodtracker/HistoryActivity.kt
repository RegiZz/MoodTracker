package task.moodtracker

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {
    private lateinit var moodStorage: MoodStorage
    private lateinit var adapter: MoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        moodStorage = MoodStorage(this)
        val moods = moodStorage.getAllMoods().toMutableList()

        adapter = MoodAdapter(moods) { index ->
            moodStorage.deleteMood(index)
            moods.removeAt(index)
            adapter.notifyItemRemoved(index)
        }
        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        btnBack.setOnClickListener { v: View? ->
            finish()
        }

    }
}
