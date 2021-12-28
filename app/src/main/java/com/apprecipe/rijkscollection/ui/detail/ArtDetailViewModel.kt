package com.apprecipe.rijkscollection.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apprecipe.rijkscollection.data.ArtDetail
import com.apprecipe.rijkscollection.data.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val KEY_OBJECT_NUMBER = "objectNumber"

@HiltViewModel
class ArtDetailViewModel @Inject constructor(
    private val repository: ArtRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState: MutableStateFlow<ArtDetailUiState> = MutableStateFlow(ArtDetailUiState.Loading)
    val uiState: StateFlow<ArtDetailUiState> get() = _uiState

    init {
        savedStateHandle.get<String>(KEY_OBJECT_NUMBER)?.let { objectNumber ->
            viewModelScope.launch {
                _uiState.value = ArtDetailUiState.Success(getArtDetailData(objectNumber))
            }
        }
    }

    suspend fun getArtDetailData(objectNumber: String): ArtDetail {
        return repository.getArtDetail(objectNumber)
    }
}

sealed class ArtDetailUiState {
    data class Success(val data: ArtDetail) : ArtDetailUiState()
    object Loading : ArtDetailUiState()
    data class Error(val exception: Throwable) : ArtDetailUiState()
}
