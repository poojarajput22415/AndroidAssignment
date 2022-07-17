package com.example.assismentuserlist.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.assismentuserlist.modle.Location
import com.example.assismentuserlist.modle.Name
import com.example.assismentuserlist.modle.Register
import com.example.assismentuserlist.modle.UserDetails
import kotlinx.coroutines.delay
import kotlin.math.max

private const val STARTING_KEY = 0
private const val LOAD_DELAY_MILLIS = 3_000L

class UserDetailsPagingSource : PagingSource<Int, UserDetails>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDetails> {
        // If params.key is null, it is the first load, so we start loading with STARTING_KEY
        val startKey = params.key ?: STARTING_KEY

        // We fetch as many articles as hinted to by params.loadSize
        val range = startKey.until(startKey + params.loadSize)

        // Simulate a delay for loads adter the initial load
        if (startKey != STARTING_KEY) delay(LOAD_DELAY_MILLIS)
        return LoadResult.Page(
            data = range.map { number ->
                UserDetails(
                    gender = "Gender $number",
                    name = Name("Title $number","first $number","last $number"),
                    location = Location("Country $number"),
                    email = "Email $number",
                    registered = Register("Date $number",number)
                )
            },
            prevKey = when (startKey) {
                STARTING_KEY -> null
                else -> when (val prevKey = ensureValidKey(key = range.first - params.loadSize)) {
                    // We're at the start, there's nothing more to load
                    STARTING_KEY -> null
                    else -> prevKey
                }
            },
            nextKey = range.last + 1
        )
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, UserDetails>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer
        val anchorPosition = state.anchorPosition ?: return null
        val userDetails = state.closestItemToPosition(anchorPosition) ?: return null
        return state.config.pageSize / 2
    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

}