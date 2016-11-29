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
        pikkelBundle.keySet().filter { !it.startsWith("pikkel:") }.forEach { pikkelBundle.remove(it) }
    }

    fun saveInstanceState(outState: Bundle?) {
        outState ?: return
        outState.putAll(pikkelBundle)
    }

    fun <T> state(initial: T): ReadWriteProperty<Pikkel, T> = State(initial)

    private class State<T>(private val initial: T) : ReadWriteProperty<Pikkel, T> {

        override fun getValue(thisRef: Pikkel, property: KProperty<*>): T {
            val key = "pikkel:" + property.name
            if (!thisRef.pikkelBundle.containsKey(key)) {
                return initial
            } else {
                @Suppress("UNCHECKED_CAST")
                return thisRef.pikkelBundle.get(key) as T
            }
        }

        override fun setValue(thisRef: Pikkel, property: KProperty<*>, value: T) {
            val key = "pikkel:" + property.name
            when (value) {
                is Bundle -> thisRef.pikkelBundle.putBundle(key, value)
                is Int -> thisRef.pikkelBundle.putInt(key, value)
                is Byte -> thisRef.pikkelBundle.putByte(key, value)
                is ByteArray -> thisRef.pikkelBundle.putByteArray(key, value)
                is Boolean -> thisRef.pikkelBundle.putBoolean(key, value)
                is BooleanArray -> thisRef.pikkelBundle.putBooleanArray(key, value)
                is Char -> thisRef.pikkelBundle.putChar(key, value)
                is CharArray -> thisRef.pikkelBundle.putCharArray(key, value)
                is Float -> thisRef.pikkelBundle.putFloat(key, value)
                is FloatArray -> thisRef.pikkelBundle.putFloatArray(key, value)
                is Parcelable -> thisRef.pikkelBundle.putParcelable(key, value)
                is Short -> thisRef.pikkelBundle.putShort(key, value)
                is ShortArray -> thisRef.pikkelBundle.putShortArray(key, value)
                is String -> thisRef.pikkelBundle.putString(key, value)
                is CharSequence -> thisRef.pikkelBundle.putCharSequence(key, value)
                is Serializable -> thisRef.pikkelBundle.putSerializable(key, value)
                null -> thisRef.pikkelBundle.putString(key, null)
                else -> throw IllegalArgumentException()
            }
        }
    }
}

class PikkelDelegate() : Pikkel {
    override val pikkelBundle: Bundle = Bundle()
}

