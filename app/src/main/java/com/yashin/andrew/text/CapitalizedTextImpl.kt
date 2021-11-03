package com.yashin.andrew.text

class CapitalizedTextImpl(
    charList: ArrayList<Char> = arrayListOf(),
    changeListener: (String, Int) -> Unit
) : TextImpl(charList, changeListener) {
    override fun enter(symb: Char) {
        super.enter(symb.uppercaseChar())
    }
}
