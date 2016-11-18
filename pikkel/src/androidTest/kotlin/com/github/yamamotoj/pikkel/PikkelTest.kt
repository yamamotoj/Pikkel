package com.github.yamamotoj.pikkel

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


    /*

public void putByte(String key, byte value)
public void putChar(String key, char value)
public void putShort(String key, short value)
public void putFloat(String key, float value)
public void putCharSequence(String key, CharSequence value)
public void putParcelable(String key, Parcelable value)
public void putSize(String key, Size value)
public void putSizeF(String key, SizeF value)
public void putParcelableArray(String key, Parcelable[] value)
public void putSerializable(String key, Serializable value)
public void putByteArray(String key, byte[] value)
public void putShortArray(String key, short[] value)
public void putCharArray(String key, char[] value)
public void putFloatArray(String key, float[] value)
public void putCharSequenceArray(String key, CharSequence[] value)
public void putBundle(String key, Bundle value)
public void putBinder(String key, IBinder value)

public void putParcelableArrayList(String key, ArrayList<? extends Parcelable> value)
public void putSparseParcelableArray(String key, SparseArray<? extends Parcelable> value)
public void putIntegerArrayList(String key, ArrayList<Integer> value)
public void putStringArrayList(String key, ArrayList<String> value)
public void putCharSequenceArrayList(String key, ArrayList<CharSequence> value)

     */


}