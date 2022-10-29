package ru.kosteloff.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.kosteloff.notes.REPOSITORY
import ru.kosteloff.notes.database.NoteDataBase
import ru.kosteloff.notes.repository.NoteRepositoryRealisation
import ru.kosteloff.notes.tablemodel.NoteModel

class StartViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    fun initDataBase() {
        val noteDataBase = NoteDataBase.getDatabase(context).getNoteDao()
        REPOSITORY = NoteRepositoryRealisation(noteDataBase)
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return REPOSITORY.getAll
    }
}