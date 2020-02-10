package com.faizikhwan.androidmvvmtutorial.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.faizikhwan.androidmvvmtutorial.db.AppDatabase
import com.faizikhwan.androidmvvmtutorial.model.Note
import com.faizikhwan.androidmvvmtutorial.repository.NoteRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val noteDao = AppDatabase.getDatabase(application, viewModelScope).noteDao()
        noteRepository = NoteRepository(noteDao)
        allNotes = noteRepository.allNotes
    }

    fun insert(note: Note) = viewModelScope.launch {
        noteRepository.insert(note)
    }
}