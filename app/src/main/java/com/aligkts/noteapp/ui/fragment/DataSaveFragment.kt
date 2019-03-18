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
import org.json.JSONArray
import java.util.*


class DataSaveFragment : Fragment() {

    private val TAG = "NoteList"
    private val prefs by lazy { PreferenceManager.getDefaultSharedPreferences(activity) }
    private val editor by lazy { prefs.edit() }
    private val listSharedPref: ArrayList<NoteDTO>? = ArrayList()


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

                editor.clear()
                editor.apply()

                listSharedPref?.add(NoteDTO(edtNote.text.toString(), 0))

                var json = Gson().toJson(listSharedPref)
                var jsonArray = JSONArray(json)

                editor.putString("jsonarray", jsonArray.toString())
                editor.apply()

                Navigation.findNavController(it).navigate(R.id.action_dataSave_to_main)

            } else {
                //show warning
            }

        }


    }


}
