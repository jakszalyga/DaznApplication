package com.example.daznapplication.ui.main


import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainActivityViewModelTest {

    private lateinit var viewModel: MainActivityViewModel

    @Before
    fun setup() {
        viewModel = MainActivityViewModel()
    }

    @Test
    fun `onEvent DismissVideoView changes state to DISMISSING_VIDEO_VIEW`() = runTest {
        viewModel.onEvent(MainActivityEvent.DismissVideoView)

        assertEquals(MainActivityStates.DISMISSING_VIDEO_VIEW, viewModel.stateFlow.first())
    }

    @Test
    fun `onEvent ToggleActionBar changes state to TOGGLING_ACTION_BAR`() = runTest {
        viewModel.onEvent(MainActivityEvent.ToggleActionBar)

        assertEquals(MainActivityStates.TOGGLING_ACTION_BAR, viewModel.stateFlow.first())
    }

    @Test
    fun `onEvent ShowVideoView changes state to SHOWING_VIDEO_VIEW and sets currentVideoUrl`() =
        runTest {
            val videoUrl = "videoUrl"

            viewModel.onEvent(MainActivityEvent.ShowVideoView(videoUrl))

            assertEquals(MainActivityStates.SHOWING_VIDEO_VIEW, viewModel.stateFlow.first())
            assertEquals(videoUrl, viewModel.currentVideoUrl)
        }
}