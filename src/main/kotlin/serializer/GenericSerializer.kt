package serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.float
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * A generic serializer for Kotlin data classes using reflection.
 *
 * This class implements [KSerializer] for any data class type [T], allowing dynamic
 * serialization and deserialization using [kotlinx.serialization.json.Json].
 *
 * @param T The type of the data class to serialize/deserialize.
 * @property kClass The [KClass] of the data class type.
 */
class GenericSerializer<T : Any>(
    private val kClass: KClass<T>,
) : KSerializer<T> {
    /**
     * Descriptor defining the structure of the serialized data.
     *
     * It dynamically builds a [SerialDescriptor] based on the primary constructor
     * parameters of the provided class.
     */
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor(kClass.simpleName ?: "Anonymous") {
            kClass.primaryConstructor?.parameters?.forEach {
                val type = it.type.classifier as? KClass<*>
                val kind =
                    when (type) {
                        Int::class -> PrimitiveKind.INT
                        String::class -> PrimitiveKind.STRING
                        Boolean::class -> PrimitiveKind.BOOLEAN
                        Double::class -> PrimitiveKind.DOUBLE
                        else -> PrimitiveKind.STRING // fallback
                    }
                element(it.name ?: "unknown", PrimitiveSerialDescriptor(it.name ?: "unknown", kind))
            }
        }

    /**
     * Serializes an instance of [T] into a JSON structure.
     *
     * Uses reflection to iterate over the properties of the instance, converting
     * each one into the appropriate [JsonElement]. Nested data classes are serialized
     * recursively using this same serializer.
     *
     * @param encoder The [Encoder] to write the JSON data to. Must be a [JsonEncoder].
     * @param value The instance of [T] to serialize.
     *
     * @throws IllegalStateException If the encoder is not a [JsonEncoder].
     */
    override fun serialize(
        encoder: Encoder,
        value: T,
    ) {
        val jsonEncoder =
            encoder as? JsonEncoder
                ?: throw IllegalStateException("This serializer only works with Json")

        val json =
            buildJsonObject {
                kClass.memberProperties.forEach { prop ->
                    val name = prop.name
                    val element =
                        when (val rawValue = prop.get(value)) {
                            is String -> JsonPrimitive(rawValue)
                            is Int -> JsonPrimitive(rawValue)
                            is Boolean -> JsonPrimitive(rawValue)
                            is Double -> JsonPrimitive(rawValue)
                            is Float -> JsonPrimitive(rawValue)
                            is Long -> JsonPrimitive(rawValue)
                            is Short -> JsonPrimitive(rawValue)
                            is Byte -> JsonPrimitive(rawValue)
                            is Enum<*> -> JsonPrimitive(rawValue.name)
                            null -> JsonNull
                            else -> {
                                // Handle nested data class with a recursive serializer
                                @Suppress("UNCHECKED_CAST")
                                val serializer = GenericSerializer(rawValue::class as KClass<Any>) as KSerializer<Any>
                                jsonEncoder.json.encodeToJsonElement(serializer, rawValue)
                            }
                        }

                    put(name, element)
                }
            }

        jsonEncoder.encodeJsonElement(json)
    }

    /**
     * Deserializes an instance of [T] from a JSON structure.
     *
     * Matches JSON fields to constructor parameters by name and type. Supports
     * nested data classes recursively. Throws if required fields are missing.
     *
     * @param decoder The [Decoder] to read the JSON data from. Must be a [JsonDecoder].
     * @return A new instance of [T] populated with values from the JSON.
     *
     * @throws IllegalStateException If the decoder is not a [JsonDecoder].
     * @throws IllegalArgumentException If a required field is missing or an unsupported type is encountered.
     */
    override fun deserialize(decoder: Decoder): T {
        val jsonDecoder =
            decoder as? JsonDecoder
                ?: throw IllegalStateException("This serializer only works with Json")

        val jsonObject = jsonDecoder.decodeJsonElement().jsonObject
        val ctor = kClass.primaryConstructor!!

        val args =
            ctor.parameters.associateWith { param ->
                val jsonValue = jsonObject[param.name] ?: throw IllegalArgumentException("Missing field ${param.name}")
                val classifier = param.type.classifier

                when (classifier) {
                    String::class -> jsonValue.jsonPrimitive.content
                    Int::class -> jsonValue.jsonPrimitive.int
                    Boolean::class -> jsonValue.jsonPrimitive.boolean
                    Double::class -> jsonValue.jsonPrimitive.double
                    Float::class -> jsonValue.jsonPrimitive.float
                    Long::class -> jsonValue.jsonPrimitive.long
                    Short::class -> jsonValue.jsonPrimitive.int.toShort()
                    Byte::class -> jsonValue.jsonPrimitive.int.toByte()
                    else -> {
                        // Deserialize nested class recursively
                        val nestedClass =
                            classifier as? KClass<*>
                                ?: throw IllegalArgumentException("Unsupported type ${param.type}")
                        val serializer = GenericSerializer(nestedClass as KClass<Any>)
                        jsonDecoder.json.decodeFromJsonElement(serializer, jsonValue)
                    }
                }
            }

        return ctor.callBy(args)
    }
} 