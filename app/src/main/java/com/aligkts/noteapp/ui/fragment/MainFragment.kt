package com.aligkts.noteapp.ui.fragment


import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aligkts.noteapp.R
import com.aligkts.noteapp.adapter.NoteAdapter
import com.aligkts.noteapp.dto.NoteDTO
import kotlinx.android.synthetic.main.fragment_main.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList


class MainFragment : Fragment() {


    private val prefs by lazy { PreferenceManager.getDefaultSharedPreferences(activity) }
    private val editor by lazy { prefs.edit() }
    private val jsonGetNote by lazy { prefs.getString("task_list", null) }

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

    }

    private fun loadData() {

        if (jsonGetNote != null) {
            val jsonArray = JSONArray(jsonGetNote)
            val listData = jsonArrayToArrayList(jsonArray)

            buildRecyclerView(listData!!)

        }

    }

    private fun buildRecyclerView(list: ArrayList<NoteDTO>) {

        val sortedList = list.sortedWith(compareByDescending { it.date })

        val viewAdapter = NoteAdapter(ArrayList(sortedList))

        recyclerNotes.apply {
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            this.adapter = viewAdapter
            this.addItemDecoration(DividerItemDecoration(activity?.applicationContext, DividerItemDecoration.VERTICAL))
            (this.adapter as NoteAdapter).notifyDataSetChanged()
        }

        val itemTouchHelperCallBack =
                object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                    override fun onMove(
                            recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                        viewAdapter.removeItem(viewHolder)


                        val jsonArray = JSONArray(jsonGetNote)
                        val size = jsonArray.length()
                        val posReverse = viewHolder.position.plus(1)

                        jsonArray.remove(size - posReverse)

                        editor.putString("task_list", jsonArray.toString())
                        editor.apply()

                        (recyclerNotes.adapter as NoteAdapter).notifyDataSetChanged()

                    }

                }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerNotes)

    }

    private fun jsonArrayToArrayList(myJsonArray: JSONArray): ArrayList<NoteDTO>? {

        val data: ArrayList<NoteDTO> = ArrayList()

        for (i in 0 until myJsonArray.length()) {
            val obj = myJsonArray.get(i) as JSONObject
            val newDTO = NoteDTO(obj.getString("note"), obj.getLong("date"))
            data.add(newDTO)
        }


        return data
    }


}


