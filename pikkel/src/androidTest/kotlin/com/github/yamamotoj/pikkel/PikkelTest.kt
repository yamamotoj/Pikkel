package com.github.yamamotoj.pikkel

import android.graphics.Point
import android.os.Bundle
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class PikkelTest {

    class PikkelTarget<T>(initial: T) : Pikkel by PikkelDelegate() {
        var stateValue: T by state(initial)
        var nullableStateValue1: T? by state(null)
        var nullableStateValue2: T? by state(initial)
    }


    @Test
    fun testInt() {
        test(0, 2)
        val target0 = PikkelTarget(0)
        assertEquals(0, target0.stateValue)
        assertEquals(null, target0.nullableStateValue1)
        assertEquals(0, target0.nullableStateValue2)

        target0.stateValue = 2
        target0.nullableStateValue1 = 2
        target0.nullableStateValue2 = null

        assertEquals(2, target0.stateValue)
        assertEquals(2, target0.nullableStateValue1)
        assertEquals(null, target0.nullableStateValue2)

        val bundle = Bundle()
        target0.saveInstanceState(bundle)

        val target1 = PikkelTarget(0)
        target1.restoreInstanceState(bundle)

        assertEquals(2, target1.stateValue)
        assertEquals(2, target1.nullableStateValue1)
        assertEquals(null, target1.nullableStateValue2)
    }

    @Test
    fun testString() {
        val target0 = PikkelTarget("initial")
        assertEquals("initial", target0.stateValue)
        assertEquals(null, target0.nullableStateValue1)
        assertEquals("initial", target0.nullableStateValue2)

        target0.stateValue = "modified"
        target0.nullableStateValue1 = "modified"
        target0.nullableStateValue2 = null

        assertEquals("modified", target0.stateValue)
        assertEquals("modified", target0.nullableStateValue1)
        assertEquals(null, target0.nullableStateValue2)

        val bundle = Bundle()
        target0.saveInstanceState(bundle)

        val target1 = PikkelTarget("initial")
        target1.restoreInstanceState(bundle)

        assertEquals("modified", target1.stateValue)
        assertEquals("modified", target1.nullableStateValue1)
        assertEquals(null, target1.nullableStateValue2)
    }

    @Test
    fun testIntArray() {
        val initial = intArrayOf(0)
        val target0 = PikkelTarget(initial)
        assertEquals(initial, target0.stateValue)
        assertEquals(null, target0.nullableStateValue1)
        assertEquals(initial, target0.nullableStateValue2)

        val modified = intArrayOf(1, 2)
        target0.stateValue = modified
        target0.nullableStateValue1 = modified
        target0.nullableStateValue2 = null

        assertEquals(modified, target0.stateValue)
        assertEquals(modified, target0.nullableStateValue1)
        assertEquals(null, target0.nullableStateValue2)

        val bundle = Bundle()
        target0.saveInstanceState(bundle)

        val target1 = PikkelTarget(modified)
        target1.restoreInstanceState(bundle)

        assertEquals(modified, target1.stateValue)
        assertEquals(modified, target1.nullableStateValue1)
        assertEquals(null, target1.nullableStateValue2)
    }

    fun <T> test(initial: T, modified: T) {
        val target0 = PikkelTarget(initial)
        assertEquals(initial, target0.stateValue)
        assertEquals(null, target0.nullableStateValue1)
        assertEquals(initial, target0.nullableStateValue2)

        target0.stateValue = modified
        target0.nullableStateValue1 = modified
        target0.nullableStateValue2 = null

        assertEquals(modified, target0.stateValue)
        assertEquals(modified, target0.nullableStateValue1)
        assertEquals(null, target0.nullableStateValue2)

        val bundle = Bundle()
        target0.saveInstanceState(bundle)

        val target1 = PikkelTarget(modified)
        target1.restoreInstanceState(bundle)

        assertEquals(modified, target1.stateValue)
        assertEquals(modified, target1.nullableStateValue1)
        assertEquals(null, target1.nullableStateValue2)
    }

    enum class TestEmun {
        A, B
    }

    /** test for all types */
    @Test
    fun testAllTypes() {
        test(1, 2)
        test(1.toByte(), 2.toByte())
        test(1.toChar(), 2.toChar())
        test(1.toShort(), 2.toShort())
        test(1.toFloat(), 2.toFloat())
        test(1.toDouble(), 2.toDouble())
        test(1.toLong(), 2.toLong())
        test(1.toString(), 2.toString())
        test(StringBuffer("1"), StringBuffer("2")) // saved as CharSequence
        test(Point(1, 1), Point(2, 2)) // saved as Parcelable
        test(TestEmun.A, TestEmun.B) // save as Serializable
        test(intArrayOf(0, 1), intArrayOf(1, 2))
        test(byteArrayOf(0, 1), byteArrayOf(1, 2))
        test(shortArrayOf(0, 1), shortArrayOf(1, 2))
        test(charArrayOf(0.toChar(), 1.toChar()), charArrayOf(1.toChar(), 2.toChar()))
        test(floatArrayOf(0f, 1f), floatArrayOf(1f, 2f))
        test(doubleArrayOf(0.toDouble(), 1.toDouble()), doubleArrayOf(1.toDouble(), 2.toDouble()))
        test(longArrayOf(0, 1), longArrayOf(1, 2))
        test(Bundle().apply { putInt("value", 1) }, Bundle().apply { putString("value", "string") })
        test(arrayListOf(Point(1, 2)), arrayListOf(Point(2, 3)))
    }
}