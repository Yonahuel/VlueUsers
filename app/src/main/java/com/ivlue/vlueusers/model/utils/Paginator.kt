package com.ivlue.vlueusers.model.utils

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}