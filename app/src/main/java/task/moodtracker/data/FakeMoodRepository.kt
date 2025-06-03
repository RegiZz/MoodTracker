package task.moodtracker.data

import java.util.UUID

object FakeMoodRepository {
    val moodList = mutableListOf<MoodEntry>()

    fun addMood(entry: MoodEntry) {
        moodList.add(0, entry)
    }

    fun getMoodById(id: UUID): MoodEntry? {
        return moodList.find { it.id == id }
    }

    fun deleteMood(id: UUID) {
        moodList.removeAll { it.id == id }
    }
}
