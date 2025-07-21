package demo

import demo.model.User
import kotlin.test.Test
import kotlin.test.assertEquals

class KotlinSerializationDemoTest {

    private val sut = KotlinSerializationDemo()

    @Test
    fun `Given User when toJson then expected result`() {
        // Given
        val expected = "{\"name\":\"Fernando\",\"yearOfBirth\":18}"
        val user = User("Fernando", 18)

        // When
        val actual = sut.toJson(user)

        // Then
        assertEquals(expected, actual)
    }

    @Test
    fun `Given Json when toModel then expected result`() {
        // Given
        val json = "{\"name\":\"Fernando\",\"yearOfBirth\":18}"
        val expected = User("Fernando", 18)

        // When
        val actual = sut.toModel(json)

        // Then
        assertEquals(expected, actual)
    }
} 