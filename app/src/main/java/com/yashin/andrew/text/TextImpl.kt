package com.yashin.andrew.text

open class TextImpl(
    val charList: ArrayList<Char> = arrayListOf(),
    val changeListener: (String, Int) -> Unit
) : Text, Iterable<Char> by charList {

    override val size: Int = charList.size

    override var curIndex: Int = 0

    override fun enter(symb: Char) {
        charList.add(curIndex, symb)
        if (curIndex < charList.size)
            curIndex++
        changeListener(charList.joinToString(separator = ""), curIndex)
    }

    override fun erase(): Char? {
        val removedIndex = curIndex - 1
        val symbol = if (removedIndex >= 0 && removedIndex < charList.size) {
            changePosition()
            charList.removeAt(removedIndex)
        } else null
        changeListener(charList.joinToString(separator = ""), curIndex)
        return symbol
    }

    private fun changePosition() {
        if (curIndex > 0)
            curIndex--
        setCursor(curIndex)
    }

    override fun setCursor(index: Int) {
        if (index >= 0 && index <= charList.size) {
            curIndex = index
            changeListener(charList.joinToString(separator = ""), curIndex)
        }
    }
}