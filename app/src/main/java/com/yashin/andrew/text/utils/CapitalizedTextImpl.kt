package com.yashin.andrew.text.utils

class CapitalizedTextImpl(
    charList: ArrayList<Char> = arrayListOf(),
    changeListener: (String, Int) -> Unit
) : TextImpl(charList, changeListener) {
    override fun enter(symb: Char) {
        super.enter(symb.uppercaseChar())
    }
}
