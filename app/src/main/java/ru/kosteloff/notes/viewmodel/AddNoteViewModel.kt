package ru.kosteloff.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kosteloff.notes.REPOSITORY
import ru.kosteloff.notes.tablemodel.NoteModel

class AddNoteViewModel : ViewModel() {

    fun insert(noteModel: NoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(noteModel)
        }
    }
}