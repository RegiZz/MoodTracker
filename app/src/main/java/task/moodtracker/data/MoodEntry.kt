package task.moodtracker.data

import java.util.UUID
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class MoodEntry(
    val id: UUID = UUID.randomUUID(),
    val date: Long = System.currentTimeMillis(),
    val mood: String,
    val note: String,
    val category: String,
    val sleptWell: Boolean,
    val wasActive: Boolean,
    val rating: Float,
    val isImportant: Boolean
) {
    fun formattedDate(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date(date))
    }
}
