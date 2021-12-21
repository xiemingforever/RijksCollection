package com.apprecipe.rijkscollection.ui.detail

import androidx.lifecycle.ViewModel
import com.apprecipe.rijkscollection.data.ArtDetail
import com.apprecipe.rijkscollection.data.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtDetailViewModel @Inject constructor(
    private val repository: ArtRepository
) : ViewModel() {

    suspend fun getArtDetailData(objectNumber: String): ArtDetail {
        return repository.getArtDetail(objectNumber)
    }
}
