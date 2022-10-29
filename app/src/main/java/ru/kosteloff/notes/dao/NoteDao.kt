package ru.kosteloff.notes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import ru.kosteloff.notes.tablemodel.NoteModel

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table")
    fun getAll(): LiveData<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteModel: NoteModel)

    @Delete
    suspend fun delete(noteModel: NoteModel)

    @Update
    suspend fun update(noteModel: NoteModel)
}