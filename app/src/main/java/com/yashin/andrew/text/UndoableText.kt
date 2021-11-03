package com.yashin.andrew.text

interface UndoableText: Text {
    fun undo()
    fun redo()
}