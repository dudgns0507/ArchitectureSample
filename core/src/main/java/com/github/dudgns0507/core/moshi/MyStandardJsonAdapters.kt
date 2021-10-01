package com.github.dudgns0507.core.moshi

import com.squareup.moshi.*
import com.squareup.moshi.internal.Util
import java.io.IOException
import java.util.*

class MyStandardJsonAdapters {
    companion object {
        val BOOLEAN_JSON_ADAPTER: JsonAdapter<Boolean> = object : JsonAdapter<Boolean>() {
            @Throws(IOException::class)
            override fun fromJson(reader: JsonReader): Boolean {
                return reader.nextBoolean()
            }

            @Throws(IOException::class)
            override fun toJson(writer: JsonWriter, value: Boolean?) {
                writer.value(value)
            }

            override fun toString(): String {
                return "JsonAdapter(Boolean)"
            }
        }

        val DOUBLE_JSON_ADAPTER: JsonAdapter<Double> = object : JsonAdapter<Double>() {
            @Throws(IOException::class)
            override fun fromJson(reader: JsonReader): Double {
                return reader.nextDouble()
            }

            @Throws(IOException::class)
            override fun toJson(writer: JsonWriter, value: Double?) {
                writer.value(value)
            }

            override fun toString(): String {
                return "JsonAdapter(Double)"
            }
        }

        val FLOAT_JSON_ADAPTER: JsonAdapter<Float> = object : JsonAdapter<Float>() {
            @Throws(IOException::class)
            override fun fromJson(reader: JsonReader): Float {
                val value = reader.nextDouble().toFloat()
                // Double check for infinity after float conversion; many doubles > Float.MAX
                if (!reader.isLenient && java.lang.Float.isInfinite(value)) {
                    throw JsonDataException(
                        "JSON forbids NaN and infinities: " + value
                                + " at path " + reader.path
                    )
                }
                return value
            }

            @Throws(IOException::class)
            override fun toJson(writer: JsonWriter, value: Float?) {
                // Manual null pointer check for the float.class adapter.
                if (value == null) {
                    throw NullPointerException()
                }
                // Use the Number overload so we write out float precision instead of double precision.
                writer.value(value)
            }

            override fun toString(): String {
                return "JsonAdapter(Float)"
            }
        }

        val INTEGER_JSON_ADAPTER: JsonAdapter<Int> = object : JsonAdapter<Int>() {
            @Throws(IOException::class)
            override fun fromJson(reader: JsonReader): Int {
                return reader.nextInt()
            }

            @Throws(IOException::class)
            override fun toJson(writer: JsonWriter, value: Int?) {
                if (value != null) {
                    writer.value(value.toLong())
                }
            }

            override fun toString(): String {
                return "JsonAdapter(Integer)"
            }
        }

        val LONG_JSON_ADAPTER: JsonAdapter<Long> = object : JsonAdapter<Long>() {
            @Throws(IOException::class)
            override fun fromJson(reader: JsonReader): Long {
                return reader.nextLong()
            }

            @Throws(IOException::class)
            override fun toJson(writer: JsonWriter, value: Long?) {
                writer.value(value)
            }

            override fun toString(): String {
                return "JsonAdapter(Long)"
            }
        }

        val STRING_JSON_ADAPTER: JsonAdapter<String> = object : JsonAdapter<String>() {
            @Throws(IOException::class)
            override fun fromJson(reader: JsonReader): String? {
                return reader.nextString()
            }

            @Throws(IOException::class)
            override fun toJson(writer: JsonWriter, value: String?) {
                writer.value(value)
            }

            override fun toString(): String {
                return "JsonAdapter(String)"
            }
        }

        private val ERROR_FORMAT = "Expected %s but was %s at path %s"

        val BYTE_JSON_ADAPTER: JsonAdapter<Byte> = object : JsonAdapter<Byte>() {
            @Throws(IOException::class)
            override fun fromJson(reader: JsonReader): Byte {
                return rangeCheckNextInt(reader, "a byte", Byte.MIN_VALUE.toInt(), 0xff).toByte()
            }

            @Throws(IOException::class)
            override fun toJson(writer: JsonWriter, value: Byte?) {
                if (value != null) {
                    writer.value((value.toInt() and 0xff).toLong())
                }
            }

            override fun toString(): String {
                return "JsonAdapter(Byte)"
            }
        }

        val CHARACTER_JSON_ADAPTER: JsonAdapter<Char> = object : JsonAdapter<Char>() {
            @Throws(IOException::class)
            override fun fromJson(reader: JsonReader): Char {
                val value = reader.nextString()
                if (value.length > 1) {
                    throw JsonDataException(String.format(ERROR_FORMAT, "a char", '"'.toString() + value + '"', reader.path))
                }
                return value[0]
            }

            @Throws(IOException::class)
            override fun toJson(writer: JsonWriter, value: Char?) {
                writer.value(value.toString())
            }

            override fun toString(): String {
                return "JsonAdapter(Character)"
            }
        }

        val SHORT_JSON_ADAPTER: JsonAdapter<Short> = object : JsonAdapter<Short>() {
            @Throws(IOException::class)
            override fun fromJson(reader: JsonReader): Short {
                return rangeCheckNextInt(reader, "a short", Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
            }

            @Throws(IOException::class)
            override fun toJson(writer: JsonWriter, value: Short?) {
                if (value != null) {
                    writer.value(value.toInt().toLong())
                }
            }

            override fun toString(): String {
                return "JsonAdapter(Short)"
            }
        }

        val FACTORY: JsonAdapter.Factory = JsonAdapter.Factory { type, annotations, moshi ->
            if (annotations.isNotEmpty()) return@Factory null
            if (type === Boolean::class.javaPrimitiveType) return@Factory BOOLEAN_JSON_ADAPTER.nullSafe()
            if (type === Byte::class.javaPrimitiveType) return@Factory BYTE_JSON_ADAPTER.nullSafe()
            if (type === Char::class.javaPrimitiveType) return@Factory CHARACTER_JSON_ADAPTER.nullSafe()
            if (type === Double::class.javaPrimitiveType) return@Factory DOUBLE_JSON_ADAPTER.nullSafe()
            if (type === Float::class.javaPrimitiveType) return@Factory FLOAT_JSON_ADAPTER.nullSafe()
            if (type === Int::class.javaPrimitiveType) return@Factory INTEGER_JSON_ADAPTER.nullSafe()
            if (type === Long::class.javaPrimitiveType) return@Factory LONG_JSON_ADAPTER.nullSafe()
            if (type === Short::class.javaPrimitiveType) return@Factory SHORT_JSON_ADAPTER.nullSafe()
            if (type === Boolean::class.java) return@Factory BOOLEAN_JSON_ADAPTER.nullSafe()
            if (type === Byte::class.java) return@Factory BYTE_JSON_ADAPTER.nullSafe()
            if (type === Char::class.java) return@Factory CHARACTER_JSON_ADAPTER.nullSafe()
            if (type === Double::class.java) return@Factory DOUBLE_JSON_ADAPTER.nullSafe()
            if (type === Float::class.java) return@Factory FLOAT_JSON_ADAPTER.nullSafe()
            if (type === Int::class.java) return@Factory INTEGER_JSON_ADAPTER.nullSafe()
            if (type === Long::class.java) return@Factory LONG_JSON_ADAPTER.nullSafe()
            if (type === Short::class.java) return@Factory SHORT_JSON_ADAPTER.nullSafe()
            if (type === String::class.java) return@Factory STRING_JSON_ADAPTER.nullSafe()
            if (type === Any::class.java) return@Factory ObjectJsonAdapter(moshi).nullSafe()
            val rawType = Types.getRawType(type)
            val generatedAdapter = Util.generatedAdapter(moshi, type, rawType)
            if (generatedAdapter != null) {
                return@Factory generatedAdapter
            }
            if (rawType.isEnum) {
                EnumJsonAdapter(rawType as Class<out Enum<*>>).nullSafe()
            } else null
        }

        @Throws(IOException::class)
        fun rangeCheckNextInt(reader: JsonReader, typeMessage: String?, min: Int, max: Int): Int {
            val value = reader.nextInt()
            if (value < min || value > max) {
                throw JsonDataException(String.format(ERROR_FORMAT, typeMessage, value, reader.path))
            }
            return value
        }

        internal class EnumJsonAdapter<T : Enum<T>>(private val enumType: Class<T>) : JsonAdapter<T>() {
            private val nameStrings: Array<String?>
            private val constants: Array<T>
            private var options: JsonReader.Options? = null

            @Throws(IOException::class)
            override fun fromJson(reader: JsonReader): T {
                val index = reader.selectString(options)
                if (index != -1) return constants[index]

                // We can consume the string safely, we are terminating anyway.
                val path = reader.path
                val name = reader.nextString()
                throw JsonDataException(
                    ("Expected one of " + listOf(*nameStrings) + " but was " + name + " at path " + path)
                )
            }

            @Throws(IOException::class)
            override fun toJson(writer: JsonWriter, value: T?) {
                writer.value(nameStrings[value!!.ordinal])
            }

            override fun toString(): String {
                return "JsonAdapter(" + enumType.name + ")"
            }

            init {
                try {
                    constants = enumType.enumConstants
                    nameStrings = arrayOfNulls(constants.size)
                    for (i in constants.indices) {
                        val constant = constants[i]
                        val annotation = enumType.getField(constant.name).getAnnotation(Json::class.java)
                        val name = annotation?.name ?: constant.name
                        nameStrings[i] = name
                    }
                    options = JsonReader.Options.of(*nameStrings)
                } catch (e: NoSuchFieldException) {
                    throw AssertionError("Missing field in " + enumType.name, e)
                }
            }
        }

        /**
         * This adapter is used when the declared type is `java.lang.Object`. Typically the runtime
         * type is something else, and when encoding JSON this delegates to the runtime type's adapter.
         * For decoding (where there is no runtime type to inspect), this uses maps and lists.
         *
         *
         * This adapter needs a Moshi instance to look up the appropriate adapter for runtime types as
         * they are encountered.
         */
        internal class ObjectJsonAdapter(private val moshi: Moshi) : JsonAdapter<Any?>() {
            private val listJsonAdapter: JsonAdapter<List<*>> = moshi.adapter<List<*>>(MutableList::class.java)
            private val mapAdapter: JsonAdapter<Map<*, *>> = moshi.adapter<Map<*, *>>(MutableMap::class.java)
            private val stringAdapter: JsonAdapter<String> = moshi.adapter(String::class.java)
            private val doubleAdapter: JsonAdapter<Double> = moshi.adapter(Double::class.java)
            private val booleanAdapter: JsonAdapter<Boolean> = moshi.adapter(Boolean::class.java)

            @Throws(IOException::class)
            override fun fromJson(reader: JsonReader): Any? {
                return when (reader.peek()) {
                    JsonReader.Token.BEGIN_ARRAY -> listJsonAdapter.fromJson(reader)
                    JsonReader.Token.BEGIN_OBJECT -> mapAdapter.fromJson(reader)
                    JsonReader.Token.STRING -> stringAdapter.fromJson(reader)
                    JsonReader.Token.NUMBER -> doubleAdapter.fromJson(reader)
                    JsonReader.Token.BOOLEAN -> booleanAdapter.fromJson(reader)
                    JsonReader.Token.NULL -> reader.nextNull()
                    else -> throw IllegalStateException(
                        "Expected a value but was " + reader.peek() + " at path " + reader.path
                    )
                }
            }

            @Throws(IOException::class)
            override fun toJson(writer: JsonWriter, value: Any?) {
                val valueClass: Class<*> = value!!.javaClass
                if (valueClass == Any::class.java) {
                    writer.beginObject()
                    writer.endObject()
                } else {
                    moshi.adapter<Any>(toJsonType(valueClass), Util.NO_ANNOTATIONS).toJson(writer, value)
                }
            }

            /**
             * Returns the type to look up a type adapter for when writing `value` to JSON. Without
             * this, attempts to emit standard types like `LinkedHashMap` would fail because Moshi doesn't
             * provide built-in adapters for implementation types. It knows how to **write**
             * those types, but lacks a mechanism to read them because it doesn't know how to find the
             * appropriate constructor.
             */
            private fun toJsonType(valueClass: Class<*>): Class<*> {
                if (MutableMap::class.java.isAssignableFrom(valueClass)) return MutableMap::class.java
                return if (MutableCollection::class.java.isAssignableFrom(valueClass)) MutableCollection::class.java else valueClass
            }

            override fun toString(): String {
                return "JsonAdapter(Object)"
            }
        }
    }
}