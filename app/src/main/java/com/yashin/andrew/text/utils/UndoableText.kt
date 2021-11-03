package com.yashin.andrew.text.utils

interface UndoableText: Text {
    fun undo()
    fun redo()
}