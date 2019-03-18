package com.aligkts.noteapp.ui.fragment


import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.aligkts.noteapp.R
import com.aligkts.noteapp.dto.NoteDTO
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_data_save.*


class DataSaveFragment : Fragment() {

    private val prefs by lazy { PreferenceManager.getDefaultSharedPreferences(activity) }
    private val editor by lazy { prefs.edit() }
    private var listSharedPref: ArrayList<NoteDTO>? = ArrayList()
    private var count: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_data_save, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        btnSave.setOnClickListener {


            if (edtNote.text != null) {

                listSharedPref?.add(NoteDTO(edtNote.text.toString(), count))
                count = count?.plus(1)
                saveData()
                /*   editor.clear()
                editor.apply()

                listSharedPref?.add(NoteDTO(edtNote.text.toString(), count))

                var json = Gson().toJson(listSharedPref)
                var jsonArray = JSONArray(json)

                editor.putString("jsonarray", jsonArray.toString())
                editor.apply()
                count++   */
                Navigation.findNavController(it).navigate(R.id.action_dataSave_to_main)

            } else {
                //show warning
            }

        }


    }

    private fun saveData() {
        val gson = Gson()
        val jsonNote = gson.toJson(listSharedPref)
        editor.putString("task_list", jsonNote)
        editor.apply()
    }


}