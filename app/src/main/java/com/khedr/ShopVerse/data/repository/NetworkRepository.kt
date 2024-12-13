package com.khedr.ShopVerse.data.repository

import com.khedr.ShopVerse.data.model.AddOrDeleteRequest
import com.khedr.ShopVerse.data.model.AddOrderRequest
import com.khedr.ShopVerse.data.model.Auth.LoginRequest
import com.khedr.ShopVerse.data.model.Auth.RegisterRequest
import com.khedr.ShopVerse.data.model.Auth.UpdateProfileRequest
import com.khedr.ShopVerse.data.model.SearchRequest
import com.khedr.ShopVerse.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun login(requestLogin: LoginRequest) = apiService.login(requestLogin)
    suspend fun register(requestRegister: RegisterRequest) = apiService.register(requestRegister)
    suspend fun getHome() = apiService.getHomeData()
    suspend fun getCategories() = apiService.getCategories()
    suspend fun getProductsByCategory(id: String) = apiService.getCategoryById(id)
    suspend fun getFavorites() = apiService.getFavorites()
    suspend fun addOrDeleteFavorite(addOrDeleteRequest:AddOrDeleteRequest) = apiService.addOrDeleteFavorite(addOrDeleteRequest)
    suspend fun search(text: String) = apiService.searchProduct(SearchRequest(text))
    suspend fun getProductById(id: String) = apiService.getProductById(id)
    suspend fun getCart() = apiService.getCart()
    suspend fun getProfile() = apiService.getProfile()
    suspend fun addOrder() = apiService.addOrders(addOrderRequest = AddOrderRequest(1,"1",2))
    suspend fun updateProfile(updateProfileRequest: UpdateProfileRequest) = apiService.updateProfile(updateProfileRequest)
    suspend fun addProductToCart(addOrDeleteRequest: AddOrDeleteRequest) = apiService.addProductToCart(addOrDeleteRequest)


}