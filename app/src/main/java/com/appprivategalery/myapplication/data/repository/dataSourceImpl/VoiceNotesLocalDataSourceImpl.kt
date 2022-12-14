package com.appprivategalery.myapplication.data.repository.dataSourceImpl

import com.appprivategalery.myapplication.data.db.VoiceNoteDao
import com.appprivategalery.myapplication.data.model.VoiceNote
import com.appprivategalery.myapplication.data.repository.dataSource.VoiceNotesLocalDataSource
import kotlinx.coroutines.flow.Flow

class VoiceNotesLocalDataSourceImpl(
    private val voiceNoteDao: VoiceNoteDao
) : VoiceNotesLocalDataSource {
    override suspend fun saveVoiceNoteToDB(voiceNote: VoiceNote) {
        voiceNoteDao.insert(voiceNote)
    }

    override fun savedVoiceNotes(): Flow<List<VoiceNote>> {
        return voiceNoteDao.getAllVoiceNotes()
    }

    override suspend fun deleteVoiceNoteFromDB(voiceNote: VoiceNote) {
        return voiceNoteDao.deleteVoiceNote(voiceNote)
    }
}