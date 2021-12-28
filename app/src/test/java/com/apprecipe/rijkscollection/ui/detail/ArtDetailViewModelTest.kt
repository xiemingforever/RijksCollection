package com.apprecipe.rijkscollection.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.apprecipe.rijkscollection.TestCoroutineRule
import com.apprecipe.rijkscollection.data.ArtRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtDetailViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: ArtDetailViewModel

    private val repository = mockk<ArtRepository>(relaxed = true)

    private val savedStateHandle = SavedStateHandle(mapOf("objectNumber" to "SK-C-5"))

    @Before
    fun setUp() {
        viewModel = ArtDetailViewModel(repository, savedStateHandle)
    }

    @Test
    fun getArtDetailData() {
        runBlockingTest {
            val objectNumber = "BK-NM-1010"
            viewModel.getArtDetailData(objectNumber)

            coVerify { repository.getArtDetail(objectNumber) }
        }
    }
}
