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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*


class MainFragment : Fragment() {


    private val prefs by lazy { PreferenceManager.getDefaultSharedPreferences(activity) }
    private val editor by lazy { prefs.edit() }
    private val gson by lazy { Gson() }
    private var listSharedPref: List<NoteDTO>? = null

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

        loadData()
        buildRecyclerView()

        /*
        val stringJson = prefs.getString("jsonarray", null)

        val jsonArray=JSONArray(stringJson)

        val note=((jsonArray[0]) as JSONObject).get("note")
        val count=((jsonArray[0]) as JSONObject).get("count")


        recyclerNotes.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = NoteAdapter(ArrayList())
            //(this.adapter as NoteAdapter).setNewList(jsonArray as List<NoteDTO>)  */
        }

    private fun loadData() {


        val jsonGetNote = prefs.getString("task_list", null)
        val gson = Gson()
        val type = object : TypeToken<ArrayList<NoteDTO>>() {}.type
        listSharedPref = gson.fromJson(jsonGetNote, type)

        if (listSharedPref == null) {
            listSharedPref = ArrayList()
        }

    }

    private fun buildRecyclerView() {
        recyclerNotes.setHasFixedSize(true)
        recyclerNotes.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerNotes.adapter = NoteAdapter(listSharedPref!!)
    }


}


