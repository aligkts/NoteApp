package com.aligkts.noteapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aligkts.noteapp.R
import com.aligkts.noteapp.dto.NoteDTO

class NoteViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(viewGroup.context)
        .inflate(R.layout.note_item,
                viewGroup,
                false)

) {

    private val txtNote by lazy { itemView.findViewById<TextView>(R.id.txtNoteItem) }

    fun bindTo(context: Context, noteDTO: NoteDTO) {

        txtNote.text = noteDTO.note

    }

}