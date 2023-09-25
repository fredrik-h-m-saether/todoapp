package com.example.tododemo

import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

//@SpringBootTest
@ExtendWith(MockKExtension::class)
class ResultDoesNotMockk {

    // FIXED ( changed mockk dependency)
    @Test
    fun simpleResultMockkTest() {
        //give
        val testmock: TestMockService = mockk()
        val expected: Result<Int> = Result.success(mockk())
        every { testmock.resultSuccess() } returns expected

        //when
        val result = testmock.resultSuccess()

        //then
        assertThat(result).isEqualTo(expected)
    }

    interface TestMockService {
        fun resultSuccess(): Result<Int>
    }
}