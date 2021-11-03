package com.yashin.andrew.text

interface Text: Iterable<Char> {
    val size: Int
    var curIndex: Int
    /**
     * Suppose here we receive only A-Za-z0-9 and whitespace chars
     */
    fun enter(symb: Char)
    fun erase(): Char?
    fun setCursor(index: Int)
}