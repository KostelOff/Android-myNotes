package ru.kosteloff.notes.repository

import androidx.lifecycle.LiveData
import ru.kosteloff.notes.tablemodel.NoteModel

interface NoteRepository {
    val getAll: LiveData<List<NoteModel>>
    suspend fun insert(noteModel: NoteModel)
    suspend fun delete(noteModel: NoteModel)
    suspend fun update(noteModel: NoteModel)
}
