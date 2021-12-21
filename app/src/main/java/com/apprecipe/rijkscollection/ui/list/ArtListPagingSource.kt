package com.apprecipe.rijkscollection.ui.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apprecipe.rijkscollection.api.RijksAPI
import com.apprecipe.rijkscollection.data.ArtItem

private const val STARTING_PAGE_INDEX = 1
private const val TOTAL_PAGES = 100

class ArtListPagingSource(
    private val api: RijksAPI
) : PagingSource<Int, ArtItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtItem> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX
            val response = api.getArtList(page = page, perPage = params.loadSize)
            LoadResult.Page(
                data = response.artObjects,
                prevKey = null, // Only paging forward.
                nextKey = if (page == TOTAL_PAGES) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
    }
}
