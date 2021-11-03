package com.yashin.andrew.text.utils

class UndoableTextImpl(private val text: Text) : UndoableText, Text by text {

    private var cmdList: MutableList<Command> = arrayListOf()
    private var cmdIndex: Int = 0

    override fun enter(symb: Char) {
        text.enter(symb)
        cmdList = cmdList.subList(0, cmdIndex)
        cmdList.add(Command(type = CommandType.ENTER, symbol = symb, index = text.curIndex))
        cmdIndex++
    }

    override fun erase(): Char? {
        val symbol = text.erase()
        cmdList = cmdList.subList(0, cmdIndex)
        cmdList.add(
            Command(
                type = CommandType.ERASE,
                symbol = symbol,
                index = if (symbol == null) text.curIndex else text.curIndex + 1
            )
        )
        cmdIndex++
        return symbol
    }

    override fun setCursor(index: Int) {
        text.setCursor(index)
        cmdList = cmdList.subList(0, cmdIndex)
        cmdList.add(Command(type = CommandType.SET_CURSOR, index = index))
        cmdIndex++
    }

    override fun undo() {
        cmdList.getOrNull(cmdIndex - 1)?.let { command ->
            when (command.type) {
                CommandType.ENTER -> text.erase()
                CommandType.ERASE -> command.symbol?.let { symbol -> text.enter(symbol) }
                CommandType.SET_CURSOR -> text.setCursor(cmdList.getOrNull(cmdIndex - 2)?.index ?: 0)
            }
            cmdIndex--
        }
    }

    override fun redo() {
        cmdList.getOrNull(cmdIndex)?.let { command ->
            when (command.type) {
                CommandType.ENTER -> command.symbol?.let { symbol -> text.enter(symbol) }
                CommandType.ERASE -> text.erase()
                CommandType.SET_CURSOR -> text.setCursor(command.index)
            }
            cmdIndex++
        }
    }
}
