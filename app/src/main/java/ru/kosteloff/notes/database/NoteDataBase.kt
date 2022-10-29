package ru.kosteloff.notes.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.kosteloff.notes.dao.NoteDao
import ru.kosteloff.notes.tablemodel.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        private var dataBase: NoteDataBase? = null

        fun getDatabase(context: Context): NoteDataBase {
            return if (dataBase == null) {
                dataBase = Room.databaseBuilder(
                    context,
                    NoteDataBase::class.java,
                    "name_database"
                ).build()
                dataBase as NoteDataBase
            } else {
                dataBase as NoteDataBase
            }
        }
    }
}