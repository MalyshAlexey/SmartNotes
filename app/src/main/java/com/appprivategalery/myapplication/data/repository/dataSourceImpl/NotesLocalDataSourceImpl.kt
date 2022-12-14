package com.appprivategalery.myapplication.data.repository.dataSourceImpl

import com.appprivategalery.myapplication.data.db.NoteDao
import com.appprivategalery.myapplication.data.model.Note
import com.appprivategalery.myapplication.data.repository.dataSource.NotesLocalDataSource
import kotlinx.coroutines.flow.Flow

class NotesLocalDataSourceImpl(
    private val noteDao: NoteDao //provide it in di.DatabaseModule
) : NotesLocalDataSource { //connects with domain.repo
    override suspend fun saveNoteToDB(note: Note) {
        return noteDao.insertNote(note)
    }

    override fun savedNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun updateNote(note: Note) {
        return noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return noteDao.deleteNote(note)
    }
}