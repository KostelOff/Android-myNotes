package ru.kosteloff.notes.repository


import androidx.lifecycle.LiveData
import ru.kosteloff.notes.dao.NoteDao
import ru.kosteloff.notes.tablemodel.NoteModel

class NoteRepositoryRealisation(private val noteDao: NoteDao) : NoteRepository {
    override val getAll: LiveData<List<NoteModel>>
        get() = noteDao.getAll()


    override suspend fun insert(noteModel: NoteModel) {
        noteDao.insert(noteModel)
    }

    override suspend fun delete(noteModel: NoteModel) {
        noteDao.delete(noteModel)
    }

    override suspend fun update(noteModel: NoteModel) {
        noteDao.update(noteModel)
    }
}