package com.aligkts.noteapp.dto

import com.google.gson.annotations.SerializedName
import java.util.*


data class NoteDTO(
    @SerializedName("note") val note: String? = "",
    @SerializedName("date") val date: Long?
)


