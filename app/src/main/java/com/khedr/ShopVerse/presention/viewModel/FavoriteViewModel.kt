    package com.khedr.ShopVerse.presention.viewModel

    import android.util.Log
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.khedr.ShopVerse.data.model.AddOrDeleteFavoriteResponse
    import com.khedr.ShopVerse.data.model.AddOrDeleteRequest
    import com.khedr.ShopVerse.data.model.FavoriteResponse
    import com.khedr.ShopVerse.data.repository.NetworkRepository
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.launch
    import javax.inject.Inject

    @HiltViewModel
    class FavoriteViewModel @Inject constructor(val repository: NetworkRepository) : ViewModel() {
        private val _favoriteResponse = MutableStateFlow<FavoriteResponse?>(null)
        val favoriteResponse : StateFlow<FavoriteResponse?> get() = _favoriteResponse
        private val _addOrDeleteFavoriteResponse = MutableStateFlow<AddOrDeleteFavoriteResponse?>(null)
        private val _isLoading = MutableStateFlow(false)
        val isLoading : StateFlow<Boolean> get() = _isLoading
        init {
            getFavorites()
        }

        fun getFavorites(){
            viewModelScope.launch {
                try {
                    _isLoading.value = true
                    val response = repository.getFavorites()
                    if (response.isSuccessful){
                        _favoriteResponse.value = response.body()
                        _isLoading.value = false
                    }else{
                        Log.d("moh", "getFavorites: ${response.message()}")
                        _isLoading.value = false
                    }
                }catch (e:Exception){
                    Log.d("moh", "getFavorites: ${e.message}")
                }finally {
                    _isLoading.value = false
                }

            }
        }

        fun addOrDeleteFavorite(id: String, onResult: (String) -> Unit) {
            viewModelScope.launch {
                try {
                    val request = AddOrDeleteRequest(id)
                    val response = repository.addOrDeleteFavorite(request)
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _addOrDeleteFavoriteResponse.value = it
                            getFavorites()
                            onResult(it.message?:"")
                        } ?: run {
                            Log.d("FavoriteViewModel", "addOrDeleteFavorite: Empty response body")
                        }
                    } else {
                        logError("addOrDeleteFavorite", response.message())
                    }
                } catch (e: Exception) {
                    logException("addOrDeleteFavorite", e)
                }
            }
        }
        private fun logError(methodName: String, errorMessage: String) {
            Log.d("FavoriteViewModel", "$methodName: Error - $errorMessage")
        }

        private fun logException(methodName: String, exception: Exception) {
            Log.e("FavoriteViewModel", "$methodName: Exception - ${exception.localizedMessage}", exception)
        }

    }