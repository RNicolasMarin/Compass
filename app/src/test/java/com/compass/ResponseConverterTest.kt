package com.compass

import com.compass.domain.ResponseConverter
import org.junit.Before
import org.junit.Test

class ResponseConverterTest {

    private lateinit var converter: ResponseConverter

    @Before
    fun setUp() {
        converter = ResponseConverter()
    }

    @Test
    fun `test Every Tenth Character Converter`() {
        val expected = listOf("s"," "," ","a","p","p","e","’","e","e","t","e","n","p","n"," ","e")
        val original = "Compass is building the first modern real estate platform, pairing the industry’s top talent with technology to make the search and sell experience intelligent and seamless."
        val result = converter.convertToEveryTenthCharacter(original)
        assert(result == expected)
    }

    @Test
    fun `test Word Counter Converter`() {
        val expected = hashMapOf("<P>" to 1, "COMPASS" to 1, "HELLO" to 1, "WORLD" to 1, "</P>" to 2)
        val original = "<p> Compass Hello </p> World </p>"
        val result = converter.convertToWordCounter(original)
        println("expected: $expected")
        println("result: $result")
        assert(result == expected)
    }
}