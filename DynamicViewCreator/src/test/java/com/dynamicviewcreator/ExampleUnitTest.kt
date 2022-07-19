package com.dynamicviewcreator

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val name = "javaScript123"
        val expected = "Java Script:"
        assertEquals(expected, name.toPropertyTitle())
    }
}