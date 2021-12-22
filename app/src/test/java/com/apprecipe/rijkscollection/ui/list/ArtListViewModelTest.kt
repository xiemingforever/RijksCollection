package com.apprecipe.rijkscollection.ui.list

import app.cash.turbine.test
import com.apprecipe.rijkscollection.TestCoroutineRule
import com.apprecipe.rijkscollection.data.ArtRepository
import com.apprecipe.rijkscollection.ui.NavDestination
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtListViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: ArtListViewModel

    private val repository = mockk<ArtRepository>(relaxed = true)

    @Before
    fun setUp() {
        viewModel = ArtListViewModel(repository)
    }

    @Test
    fun getArtListData() {
        viewModel.getArtListData()

        verify { repository.getArtListStream() }
    }

    @Test
    fun onItemClicked() {
        runBlockingTest {
            viewModel.navDestination.test {
                val objectNumber = "BK-NM-1010"
                viewModel.onItemClicked(objectNumber)

                assertEquals(NavDestination.ArtDetail(objectNumber), expectMostRecentItem())

                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
