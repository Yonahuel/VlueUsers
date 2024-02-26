package com.ivlue.vlueusers.model.utils

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

    override fun reset() {
        currentKey = initialKey
    }
}