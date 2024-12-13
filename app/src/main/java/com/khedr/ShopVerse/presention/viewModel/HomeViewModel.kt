package com.khedr.ShopVerse.presention.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khedr.ShopVerse.data.model.home.Categories
import com.khedr.ShopVerse.data.model.home.HomeResponse
import com.khedr.ShopVerse.data.model.home.ProductByCategoriesResponse
import com.khedr.ShopVerse.data.model.home.Products
import com.khedr.ShopVerse.data.network.AuthInterceptor
import com.khedr.ShopVerse.data.repository.NetworkRepository
import com.khedr.ShopVerse.util.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: NetworkRepository,
    val appPreferences: AppPreferences,
    val authInterceptor: AuthInterceptor) : ViewModel() {
    private val _homeResponse = MutableStateFlow<HomeResponse?>(null)
    val homeResponse : StateFlow<HomeResponse?> get() = _homeResponse
    private val _categoriesResponse = MutableStateFlow<Categories?>(null)
    val categoriesResponse : StateFlow<Categories?> get() = _categoriesResponse
    private val _categoriesById = MutableStateFlow<ProductByCategoriesResponse?>(null)
    val categoriesById : StateFlow<ProductByCategoriesResponse?> get() = _categoriesById
    private val _isLoading = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> get() = _isLoading

    init {
        getHome()
        getCategories()
    }

    fun getHome(){
        appPreferences.init()
        if (appPreferences.getString("token","")!=""){
            authInterceptor.setToken(appPreferences.getString("token","").toString())
        }
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.getHome()
                if (response.isSuccessful){
                    _homeResponse.value = response.body()
                    Log.d("moh", "getHome: ${homeResponse.value}")
                    _isLoading.value = false
                }else{
                    Log.d("moh", "getHome: ${response.message()}")
                    _isLoading.value = false
                }
            }catch (e:Exception){
                Log.d("moh", "getHome: ${e.message}")
            }finally {
                _isLoading.value = false
            }

        }
    }
    fun getCategories(){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.getCategories()
                if (response.isSuccessful){
                    _categoriesResponse.value = response.body()
                }else{
                    Log.d("moh", "getCategories: ${response.message()}")
                }
            }catch (e:Exception){
                Log.d("moh", "getCategories: ${e.message}")
            }

        }
    }
    fun getProductsByCategories(id:String){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.getProductsByCategory(id)
                if (response.isSuccessful){
                    _categoriesById.value = response.body()
                    _isLoading.value = false
                }else{
                    Log.d("moh", "getCategories: ${response.message()}")
                    _isLoading.value = false
                }
            }catch (e:Exception){
                Log.d("moh", "getCategories: ${e.message}")
                _isLoading.value = false
            }

        }
    }

}