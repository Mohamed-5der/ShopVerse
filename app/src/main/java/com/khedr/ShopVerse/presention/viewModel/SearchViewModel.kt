package com.khedr.ShopVerse.presention.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khedr.ShopVerse.data.model.AddOrDeleteRequest
import com.khedr.ShopVerse.data.model.SearchResponse
import com.khedr.ShopVerse.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository: NetworkRepository): ViewModel() {
  private val _searchResponse = MutableStateFlow<SearchResponse?>(null)
  val searchResponse: StateFlow<SearchResponse?> get() = _searchResponse

  fun search( query: String) {
    viewModelScope.launch {
      try {
        val response = repository.search(query)
        if (response.isSuccessful) {
             response.body()?.let {
               if (it.status == true){
                 _searchResponse.value = it
               }
          } ?: run {
            Log.d("SearchViewModel", "search: Empty response body")
          }
        } else {
          Log.d("SearchViewModel", "search: ${response.message()}")
        }
      } catch (e: Exception) {
        Log.e("SearchViewModel", "search: Exception - ${e.localizedMessage}", e)
      }
    }
  }

}