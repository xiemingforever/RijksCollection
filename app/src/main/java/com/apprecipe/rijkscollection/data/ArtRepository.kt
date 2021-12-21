package com.apprecipe.rijkscollection.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apprecipe.rijkscollection.api.NETWORK_PAGE_SIZE
import com.apprecipe.rijkscollection.api.RijksAPI
import com.apprecipe.rijkscollection.ui.list.ArtListPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val api: RijksAPI
) {

    fun getArtListStream(): Flow<PagingData<ArtItem>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { ArtListPagingSource(api) }
        ).flow
    }

    suspend fun getArtDetail(objectNumber: String): ArtDetail =
        api.getArtDetail(objectNumber).artDetail
}
