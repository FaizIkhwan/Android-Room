package com.faizikhwan.androidmvvmtutorial.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note (

    @PrimaryKey(autoGenerate = true)
    private var id: Int,
    private var title: String,
    private var description: String,
    private var priority: Int
)