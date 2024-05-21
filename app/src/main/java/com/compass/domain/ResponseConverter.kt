package com.compass.domain

class ResponseConverter {

    fun convertToEveryTenthCharacter(original: String): List<String> {
        var index = -1
        val list = mutableListOf<String>()
        while (index < original.length) {
            index += 10
            if (index < original.length) {
                list.add(original[index].toString())
            }
        }
        return list
    }

    fun convertToWordCounter(original: String): Map<String, Int> {
        val wordAndCounter = hashMapOf<String, Int>()
        val withoutSpaces = original.split(" ")
        for (word in withoutSpaces) {
            val wordToUse = word.uppercase()
            val amount = wordAndCounter.getOrDefault(wordToUse, 0)
            wordAndCounter[wordToUse] = amount + 1
        }
        return wordAndCounter
    }
}