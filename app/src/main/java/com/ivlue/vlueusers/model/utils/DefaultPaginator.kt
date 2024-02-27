package com.ivlue.vlueusers.model.utils

/**
 * Default implementation of a paginator for loading paginated data.
 * @param Page The type representing the page number.
 * @param Item The type representing the items to be paginated.
 * @property initialKey The initial page key to start pagination from.
 * @property onLoadUpdated Callback function invoked to update the loading state.
 * @property onRequest Function to request the next page of items asynchronously.
 * @property getNextKey Function to calculate the next page key based on the retrieved items.
 * @property onError Callback function invoked when an error occurs during pagination.
 * @property onSuccess Callback function invoked when items are successfully retrieved.
 */
class DefaultPaginator<Page, Item>(
    private val initialKey: Page,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Page) -> Result<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Page,
    private inline val onError: suspend (Throwable) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Page) -> Unit
): Paginator<Page, Item> {
    private var currentKey = initialKey
    private var isMakingRequest = false

    /**
     * Loads the next page of items.
     */
    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        val items = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

    /**
     * Resets the paginator to its initial state.
     */
    override fun reset() {
        currentKey = initialKey
    }
}