package com.yashin.andrew.text.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.yashin.andrew.text.R
import com.yashin.andrew.text.utils.CapitalizedTextImpl
import com.yashin.andrew.text.utils.TextImpl
import com.yashin.andrew.text.utils.UndoableTextImpl

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var resultCursor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultText = findViewById(R.id.result_text)
        resultCursor = findViewById(R.id.result_cursor)

        val enterEdit = findViewById<EditText>(R.id.enter_edit)
        val enterButton = findViewById<Button>(R.id.enter_button)

        val eraseButton = findViewById<Button>(R.id.erase_button)
        val undoButton = findViewById<Button>(R.id.undo_button)
        val redoButton = findViewById<Button>(R.id.redo_button)

        val cursorEdit = findViewById<EditText>(R.id.set_cursor_edit)
        val cursorButton = findViewById<Button>(R.id.set_cursor_button)

        val caseCheckBox = findViewById<CheckBox>(R.id.case_check_box)

        var text = UndoableTextImpl(TextImpl { result, index ->
            resultText.text = getString(R.string.text_message, result)
            resultCursor.text = getString(R.string.cursor_message, index.toString())
        })

        caseCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                text = UndoableTextImpl(CapitalizedTextImpl { result, index ->
                    resultText.text = getString(R.string.text_message, result)
                    resultCursor.text = getString(R.string.cursor_message, index.toString())
                })
            } else {
                text = UndoableTextImpl(TextImpl { result, index ->
                    resultText.text = getString(R.string.text_message, result)
                    resultCursor.text = getString(R.string.cursor_message, index.toString())
                })
            }
            resetUI()
        }

        enterButton.setOnClickListener {
            enterEdit.text.firstOrNull()?.let {
                text.enter(it)
                enterEdit.setText("")
            }
        }

        eraseButton.setOnClickListener {
            text.erase()
        }

        undoButton.setOnClickListener {
            text.undo()
        }

        redoButton.setOnClickListener {
            text.redo()
        }

        cursorButton.setOnClickListener {
            cursorEdit.text.firstOrNull()?.digitToIntOrNull()?.let {
                text.setCursor(it)
                cursorEdit.setText("")
            }
        }
        resetUI()
    }

    private fun resetUI() {
        resultText.text = getString(R.string.text_message, "")
        resultCursor.text = getString(R.string.cursor_message, "0")
    }
}