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

/**
 * ViewModel class responsible for managing application-level data and UI state.
 *
 * @param application The application context.
 * @param repository The repository for accessing user data.
 */
@HiltViewModel
class AppViewModel @Inject constructor(
    application: Application,
    private val repository: UserRepository
): AndroidViewModel(application) {
    // Current User
    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    // Screen state
    var state by mutableStateOf(ScreenState())

    // Paginator for loading and managing user data
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
        // Load the initial set of items when the ViewModel is created
        loadNextItems()
    }

    /**
     * Loads the next set of items from the repository.
     */
    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    /**
     * Sets the current user.
     *
     * @param user The user to set.
     */
    fun setUser(user: User) {
        _user.value = user
    }
}

/**
 * Represents the state of the screen.
 *
 * @param isLoading Indicates whether data is currently being loaded.
 * @param items The list of items to display on the screen.
 * @param error An optional error message if an error occurs.
 * @param endReached Indicates whether the end of the data set has been reached.
 * @param page The current page number for pagination.
 */
data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<User> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)