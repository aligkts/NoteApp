package com.aligkts.noteapp.ui.fragment


import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.aligkts.noteapp.R
import com.aligkts.noteapp.dto.NoteDTO
import com.aligkts.noteapp.helper.hideKeyboard
import kotlinx.android.synthetic.main.fragment_data_save.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*


class DataSaveFragment : Fragment() {

    private val prefs by lazy { PreferenceManager.getDefaultSharedPreferences(activity) }
    private val editor by lazy { prefs.edit() }
    private val jsonSharedNode by lazy { prefs.getString("task_list", null) }
    private var jsonArray: JSONArray = JSONArray()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_data_save, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSave.setOnClickListener {

            val userEntry = edtNote.text.toString().trim()

            if (TextUtils.isEmpty(userEntry)) {
                edtNote.error = "Write something"
                return@setOnClickListener

            } else {

                if (jsonSharedNode != null) {
                    jsonArray = JSONArray(jsonSharedNode)
                } else {
                    jsonArray = JSONArray()
                }


                val currentDate = Date()
                val text = edtNote.text.toString()
                val obj = JSONObject()
                obj.put("note", text)
                obj.put("date", currentDate.time.toLong())

                jsonArray.put(obj)

                saveData(jsonArray)

                navigateToSomewhere(view, R.id.action_dataSave_to_main)

                view.hideKeyboard(activity!!)

            }

        }

    }

    private fun saveData(jsonArray: JSONArray) {

        editor.putString("task_list", jsonArray.toString())
        editor.apply()
    }

    private fun clearSharedPref() {
        editor.clear()
        editor.apply()
    }

    private fun navigateToSomewhere(view: View, actionId: Int) {
        Navigation.findNavController(view).navigate(actionId)

    }

}