package com.apprecipe.rijkscollection.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.apprecipe.rijkscollection.data.ArtItem
import com.apprecipe.rijkscollection.data.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(
    private val repository: ArtRepository
) : ViewModel() {

    fun getArtListData(): Flow<PagingData<ArtItem>> {
        return repository.getArtListStream().cachedIn(viewModelScope)
    }
}
