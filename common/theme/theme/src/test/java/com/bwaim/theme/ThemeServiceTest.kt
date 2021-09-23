package com.bwaim.theme

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test

internal class ThemeServiceTest {
    @RelaxedMockK
    private lateinit var themeRepository: ThemeRepository
    private lateinit var themeService: ThemeService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        themeService = ThemeService(
            ioDispatcher = TestCoroutineDispatcher(),
            themeRepository = themeRepository
        )
    }

    @Test
    fun shouldReturnThemeFlow_whenObserved() = runBlocking {
        val expectedThemes = arrayOf(Theme.SYSTEM, Theme.LIGHT)
        every { themeRepository.observeTheme() } returns flowOf(*expectedThemes)

        val themes = themeService
            .observeTheme()
            .toList()
            .toTypedArray()

        assertThat(themes).isEqualTo(expectedThemes)
    }

    @Test
    fun shouldSaveTheme_whenSet() = runBlocking {
        val theme = Theme.SYSTEM

        themeService.setTheme(theme)

        coVerify { themeRepository.setTheme(theme) }
    }
}
