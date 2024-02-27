package com.ivlue.vlueusers.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ivlue.vlueusers.model.network.entities.User
import com.ivlue.vlueusers.model.repositories.UserRepository
import com.ivlue.vlueusers.model.utils.DefaultPaginator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    application: Application,
    private val repository: UserRepository
): AndroidViewModel(application) {
    // Current User
    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    var state by mutableStateOf(ScreenState())
    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.getItems(nextPage)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun setUser(user: User) {
        _user.value = user
    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<User> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)