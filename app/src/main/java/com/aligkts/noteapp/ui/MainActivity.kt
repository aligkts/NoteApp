package com.aligkts.noteapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.aligkts.noteapp.R
import com.aligkts.noteapp.ui.fragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onBackPressed() {
        when (NavHostFragment.findNavController(nav_host_fragment).currentDestination?.id) {
            R.id.mainFragment -> {
                showQuitDialog()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }


    private fun showQuitDialog() {
        val alertDialog = AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit from app?")
                .setNegativeButton("Hayır") { dialog, which -> }
                .setPositiveButton("Evet") { dialog, which ->
                    this.finish()
                }.show()
    }


}
