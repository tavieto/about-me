package com.tavieto.aboutme

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.tavieto.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            addNickname(it)
        }

        binding.nicknameText.setOnClickListener {
            updateNickname(binding.nicknameEdit)
        }
    }

    private fun addNickname(view: View) {
        binding.nicknameEdit.visibility = View.GONE
        binding.button.visibility = View.GONE

        binding.nicknameText.text = binding.nicknameEdit.text.toString()
        binding.nicknameText.visibility = View.VISIBLE

        // Hide the keyboard
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname(view: View) {
        binding.nicknameEdit.visibility = View.VISIBLE
        binding.button.visibility = View.VISIBLE
        binding.nicknameText.visibility = View.GONE

        binding.nicknameEdit.requestFocus()

        // Show the keyboard
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, 0)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                addNickname(binding.button)
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }
}