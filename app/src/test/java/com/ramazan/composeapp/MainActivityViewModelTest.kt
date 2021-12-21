package com.ramazan.composeapp

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


class MainActivityViewModelTest {

    private val repository = mockk<TextRepository>()

    @Test
    fun `when onChangeText repository getText invoked`() {
        val predictedResult = "Test"
        every { repository.getText() } returns predictedResult
        val viewModel = MainViewModel(repository)
        viewModel.onTextChange()
        assertEquals(predictedResult, viewModel.text)
        verify { repository.getText() }
    }

    @Test
    fun `when onChangeText emit different item`() {
        val list = arrayListOf(
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
        )
        val repository = TextRepositoryImpl(list)
        val viewModel = MainViewModel(repository)
        viewModel.onTextChange()
        val result1 = viewModel.text
        viewModel.onTextChange()
        val result2 = viewModel.text
        viewModel.onTextChange()
        val result3 = viewModel.text
        assertContains(list, result1)
        assertContains(list, result2)
        assertContains(list, result3)
        assertNotEquals(result1, result2)
        assertNotEquals(result2, result3)
    }
}