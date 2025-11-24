package com.example.lab_week_11_a

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import android.widget.TextView
import android.widget.EditText
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settingsStore = (application as SettingsApplication).settingsStore

        val settingsViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SettingsViewModel(settingsStore) as T
            }
        })[SettingsViewModel::class.java]

        settingsViewModel.textLiveData.observe(this
        ) {
            findViewById<TextView>(
                R.id.activity_main_text_view
            ).text = it
        }

        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            settingsViewModel.saveText(
                findViewById<EditText>(R.id.activity_main_edit_text)
                    .text.toString()
            )
            findViewById<EditText>(R.id.activity_main_edit_text).setText("")
        }
    }
}