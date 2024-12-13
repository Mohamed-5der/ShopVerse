package com.khedr.ShopVerse.presention.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khedr.ShopVerse.data.model.DetailsProductResponse
import com.khedr.ShopVerse.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsProductViewModel@Inject constructor(private val networkRepository: NetworkRepository) :ViewModel() {
    private val _detailsProductResponse = MutableStateFlow<DetailsProductResponse?>(null)
    val detailsProductResponse: StateFlow<DetailsProductResponse?> = _detailsProductResponse
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getProductById(id: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = networkRepository.getProductById(id)
                if (response.isSuccessful){
                    if (response.body()?.status == true){
                        _detailsProductResponse.value = response.body()
                    }else{
                        Log.d("TAG", "getProductById: ${response.body()?.message}")
                    }
                    _isLoading.value = false
                }else{
                    Log.d("TAG", "getProductById: ${response.message()}")
                }
            }catch (e: Exception){
                Log.d("TAG", "getProductById: ${e.message}")
                _isLoading.value = false
            }finally {
                _isLoading.value = false
            }
        }
    }
}