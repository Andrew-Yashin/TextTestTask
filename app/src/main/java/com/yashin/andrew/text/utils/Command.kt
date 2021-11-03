package com.yashin.andrew.text.utils

data class Command(
    val index: Int,
    val symbol: Char? = null,
    val type: CommandType
)

enum class CommandType {
    ENTER,
    ERASE,
    SET_CURSOR,
}
