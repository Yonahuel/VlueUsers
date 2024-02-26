package com.ivlue.vlueusers.model.utils

interface Paginator<Page, Item> {
    suspend fun loadNextItems()
    fun reset()
}