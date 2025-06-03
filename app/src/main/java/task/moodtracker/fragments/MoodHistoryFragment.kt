package task.moodtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import task.moodtracker.R
import task.moodtracker.adapter.MoodAdapter
import task.moodtracker.data.FakeMoodRepository

class MoodHistoryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mood_history, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.moodRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MoodAdapter(FakeMoodRepository.moodList) { mood ->
            val action = MoodHistoryFragmentDirections.actionMoodHistoryFragmentToMoodDetailsFragment(mood.id.toString())
            findNavController().navigate(action)
        }
        return view
    }
}
