package com.aligkts.noteapp.ui.fragment


import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.aligkts.noteapp.R
import com.aligkts.noteapp.adapter.NoteAdapter
import com.aligkts.noteapp.dto.NoteDTO
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private val prefs by lazy { PreferenceManager.getDefaultSharedPreferences(activity) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgAdd.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_main_to_dataSave)
        }

    }

    override fun onStart() {
        super.onStart()

        val json_array = prefs.getString("jsonarray", null)

        recyclerNotes.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = NoteAdapter(ArrayList())
            (this.adapter as NoteAdapter).setNewList(json_array as List<NoteDTO>)
        }

    }

}
