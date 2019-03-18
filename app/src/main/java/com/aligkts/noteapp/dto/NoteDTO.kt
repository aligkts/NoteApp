package com.aligkts.noteapp.dto

import com.google.gson.annotations.SerializedName


data class NoteDTO(
    @SerializedName("note") val note: String? = "",
    @SerializedName("count") val count: Int? = 0
)