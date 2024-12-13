package com.khedr.ShopVerse.presention.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khedr.ShopVerse.data.model.AddOrDeleteRequest
import com.khedr.ShopVerse.data.model.AddToCartResponse
import com.khedr.ShopVerse.data.model.CartResponse
import com.khedr.ShopVerse.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel@Inject constructor(
    private val repository: NetworkRepository
) :ViewModel() {
    private  val _cartResponse = MutableStateFlow<CartResponse?>(null)
    val cartResponse: StateFlow<CartResponse?> get() = _cartResponse
    private val _addOrDeleteToCart = MutableStateFlow<AddToCartResponse?>(null)
    private val _isLoading = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> get() = _isLoading

    init {
        getCart()
    }

    fun getCart(){
        viewModelScope.launch {
            _isLoading.value =true
            try {
                val response =repository.getCart()
                if (response.isSuccessful){
                        _cartResponse.value = response.body()
                        _isLoading.value =false
                }else{
                    Log.e("CartViewModel", "Error getting cart: ${response.code()}")
                    _isLoading.value =false
                }
            }catch (e:Exception) {
                Log.e("CartViewModel", "Error getting cart", e)
                _isLoading.value =false
            }
        }
    }
    fun addOrder(
        onResult: (String) -> Unit
    ){
        viewModelScope.launch {
            try {
                val response = repository.addOrder()
                if (response.isSuccessful){
                    response.body()?.let {
                        onResult(it.message?:"")
                    } ?: run {
                        Log.d("CartViewModel", "addOrder: Empty response body")
                    }
                }
            }catch (e :Exception){
                Log.e("CartViewModel", "Error getting cart", e)

            }
        }
    }


    fun addOrDeleteProductToCart(id: String, onResult: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val request = AddOrDeleteRequest(id)
                val response = repository.addProductToCart(request)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _addOrDeleteToCart.value = it
                        getCart()
                        onResult(it.message?:"")
                    } ?: run {
                        Log.d("FavoriteViewModel", "addOrDeleteFavorite: Empty response body")
                    }
                } else {
                    Log.d("FavoriteViewModel", "addOrDeleteFavorite: Error - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("FavoriteViewModel", "addOrDeleteFavorite: Exception - ${e.localizedMessage}", e)

            }
        }
    }

}