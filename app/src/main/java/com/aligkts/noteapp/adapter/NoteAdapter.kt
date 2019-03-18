package com.aligkts.noteapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aligkts.noteapp.dto.NoteDTO

class NoteAdapter(var itemList: List<NoteDTO>) : RecyclerView.Adapter<NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        return NoteViewHolder(parent)
    }

    override fun getItemCount(): Int {

        return itemList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.bindTo(holder.itemView.context, itemList[position])
    }

    fun setNewList(itemList: List<NoteDTO>) {

        this.itemList = itemList
        notifyDataSetChanged()
    }

}