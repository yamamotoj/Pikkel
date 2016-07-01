package com.github.yamamotoj.pikkel

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface Pikkel {
    val bundle: Bundle
    fun restoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState ?: return
        this.bundle.putAll(savedInstanceState)
    }

    fun saveInstanceState(outState: Bundle?) {
        outState ?: return
        outState.putAll(bundle)
    }

    fun <T> state(initial: T): ReadWriteProperty<Pikkel, T> = State(initial)

    private class State<T>(val initial: T) : ReadWriteProperty<Pikkel, T> {

        private var useInitial: Boolean = true
        override fun getValue(thisRef: Pikkel, property: KProperty<*>): T {
            if (useInitial) {
                return initial
            } else {
                @Suppress("UNCHECKED_CAST")
                return thisRef.bundle.get(property.name) as T
            }
        }

        override fun setValue(thisRef: Pikkel, property: KProperty<*>, value: T) {
            useInitial = false
            value ?: return thisRef.bundle.remove(property.name)
            when (value) {
                is Bundle -> thisRef.bundle.putBundle(property.name, value)
                is Int -> thisRef.bundle.putInt(property.name, value)
                is Byte -> thisRef.bundle.putByte(property.name, value)
                is ByteArray -> thisRef.bundle.putByteArray(property.name, value)
                is Boolean -> thisRef.bundle.putBoolean(property.name, value)
                is BooleanArray -> thisRef.bundle.putBooleanArray(property.name, value)
                is Char -> thisRef.bundle.putChar(property.name, value)
                is CharArray -> thisRef.bundle.putCharArray(property.name, value)
                is CharSequence -> thisRef.bundle.putCharSequence(property.name, value)
                is Float -> thisRef.bundle.putFloat(property.name, value)
                is FloatArray -> thisRef.bundle.putFloatArray(property.name, value)
                is Parcelable -> thisRef.bundle.putParcelable(property.name, value)
                is Serializable -> thisRef.bundle.putSerializable(property.name, value)
                is Short -> thisRef.bundle.putShort(property.name, value)
                is ShortArray -> thisRef.bundle.putShortArray(property.name, value)
                is Serializable -> thisRef.bundle.putSerializable(property.name, value)
                is String -> thisRef.bundle.putString(property.name, value)
                else -> IllegalArgumentException()
            }
        }
    }
}

class PikkelDelegate() : Pikkel {
    override val bundle: Bundle = Bundle()
}

