package com.ivlue.vlueusers.model.utils

/**
 * Interface representing a paginator for loading paginated data.
 * @param Page The type representing the page number.
 * @param Item The type representing the items to be paginated.
 */
interface Paginator<Page, Item> {
    /**
     * Asynchronously loads the next page of items.
     */
    suspend fun loadNextItems()
    /**
     * Resets the paginator state.
     */
    fun reset()
}