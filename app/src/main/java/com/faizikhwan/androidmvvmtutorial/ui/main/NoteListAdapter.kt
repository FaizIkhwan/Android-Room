package com.faizikhwan.androidmvvmtutorial.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faizikhwan.androidmvvmtutorial.R
import com.faizikhwan.androidmvvmtutorial.model.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>() // Cached copy of words

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.textView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]
        holder.textView.text = current.toString()
    }

    internal fun setWords(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}