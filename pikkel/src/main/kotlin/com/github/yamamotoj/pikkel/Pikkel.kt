package com.github.yamamotoj.pikkel

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


interface Pikkel {
    val pikkelBundle: Bundle
    fun restoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState ?: return
        pikkelBundle.putAll(savedInstanceState)
    }

    fun saveInstanceState(outState: Bundle?) {
        outState ?: return
        outState.putAll(pikkelBundle)
    }

    fun <T> state(initial: T): ReadWriteProperty<Pikkel, T> = State(initial)

    private class State<T>(private val initial: T) : ReadWriteProperty<Pikkel, T> {

        override fun getValue(thisRef: Pikkel, property: KProperty<*>): T {
            if (!thisRef.pikkelBundle.containsKey(property.name)) {
                return initial
            } else {
                @Suppress("UNCHECKED_CAST")
                return thisRef.pikkelBundle.get(property.name) as T
            }
        }

        override fun setValue(thisRef: Pikkel, property: KProperty<*>, value: T) {
            when (value) {
                is Bundle -> thisRef.pikkelBundle.putBundle(property.name, value)
                is Int -> thisRef.pikkelBundle.putInt(property.name, value)
                is IntArray -> thisRef.pikkelBundle.putIntArray(property.name, value)
                is Byte -> thisRef.pikkelBundle.putByte(property.name, value)
                is ByteArray -> thisRef.pikkelBundle.putByteArray(property.name, value)
                is Boolean -> thisRef.pikkelBundle.putBoolean(property.name, value)
                is BooleanArray -> thisRef.pikkelBundle.putBooleanArray(property.name, value)
                is Char -> thisRef.pikkelBundle.putChar(property.name, value)
                is CharArray -> thisRef.pikkelBundle.putCharArray(property.name, value)
                is Float -> thisRef.pikkelBundle.putFloat(property.name, value)
                is FloatArray -> thisRef.pikkelBundle.putFloatArray(property.name, value)
                is Short -> thisRef.pikkelBundle.putShort(property.name, value)
                is ShortArray -> thisRef.pikkelBundle.putShortArray(property.name, value)
                is String -> thisRef.pikkelBundle.putString(property.name, value)
                is CharSequence -> thisRef.pikkelBundle.putCharSequence(property.name, value)
                is Serializable -> thisRef.pikkelBundle.putSerializable(property.name, value)
                is Parcelable -> thisRef.pikkelBundle.putParcelable(property.name, value)
                null -> thisRef.pikkelBundle.putString(property.name, null)
                else -> throw IllegalArgumentException("object should be Serializable or Parcelable")
            }
        }
    }
}

class PikkelDelegate() : Pikkel {
    override val pikkelBundle: Bundle = Bundle()
}

