package com.apprecipe.rijkscollection.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.apprecipe.rijkscollection.data.ArtItem
import com.apprecipe.rijkscollection.data.ArtRepository
import com.apprecipe.rijkscollection.ui.NavDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(
    private val repository: ArtRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArtListUiState.Success(PagingData.empty()))
    val uiState: StateFlow<ArtListUiState> = _uiState

    private val _navDestination = MutableSharedFlow<NavDestination>(replay = 0)
    val navDestination: SharedFlow<NavDestination>
        get() = _navDestination

    init {
        viewModelScope.launch {
            getArtListData().collect {
                _uiState.value = ArtListUiState.Success(it)
            }
        }
    }

    fun getArtListData(): Flow<PagingData<ArtItem>> {
        return repository.getArtListStream().cachedIn(viewModelScope)
    }

    fun onItemClicked(objectNumber: String) {
        viewModelScope.launch {
            _navDestination.emit(NavDestination.ArtDetail(objectNumber))
        }
    }
}

sealed class ArtListUiState {
    data class Success(val data: PagingData<ArtItem>) : ArtListUiState()
    data class Error(val exception: Throwable) : ArtListUiState()
}
