package com.faizikhwan.androidmvvmtutorial.repository

import androidx.lifecycle.LiveData
import com.faizikhwan.androidmvvmtutorial.db.dao.NoteDao
import com.faizikhwan.androidmvvmtutorial.model.Note

public class NoteRepository(private var noteDao: NoteDao) {

    var allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }

    suspend fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }
}