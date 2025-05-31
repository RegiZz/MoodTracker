package task.moodtracker

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MoodStorage(context: Context) {
    private val prefs = context.getSharedPreferences("moods", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val key = "mood_list"

    fun saveMood(mood: Mood) {
        val moods = getAllMoods().toMutableList()
        moods.add(0, mood)
        prefs.edit().putString(key, gson.toJson(moods)).apply()
    }

    fun getAllMoods(): List<Mood> {
        val json = prefs.getString(key, null)
        return if (json != null) {
            gson.fromJson(json, object : TypeToken<List<Mood>>() {}.type)
        } else emptyList()
    }

    fun deleteMood(index: Int) {
        val moods = getAllMoods().toMutableList()
        if (index in moods.indices) {
            moods.removeAt(index)
            prefs.edit().putString(key, gson.toJson(moods)).apply()
        }
    }
}