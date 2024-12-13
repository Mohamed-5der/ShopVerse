package com.khedr.ShopVerse.data.network

import com.khedr.ShopVerse.data.model.AddOrDeleteFavoriteResponse
import com.khedr.ShopVerse.data.model.AddOrDeleteRequest
import com.khedr.ShopVerse.data.model.AddOrderRequest
import com.khedr.ShopVerse.data.model.AddOrderResponse
import com.khedr.ShopVerse.data.model.AddToCartResponse
import com.khedr.ShopVerse.data.model.Auth.LoginRequest
import com.khedr.ShopVerse.data.model.Auth.LoginResponse
import com.khedr.ShopVerse.data.model.Auth.RegisterRequest
import com.khedr.ShopVerse.data.model.Auth.RegisterResponse
import com.khedr.ShopVerse.data.model.Auth.UpdateProfileRequest
import com.khedr.ShopVerse.data.model.Auth.UpdateProfileResponse
import com.khedr.ShopVerse.data.model.CartResponse
import com.khedr.ShopVerse.data.model.DetailsProductResponse
import com.khedr.ShopVerse.data.model.FavoriteResponse
import com.khedr.ShopVerse.data.model.SearchRequest
import com.khedr.ShopVerse.data.model.SearchResponse
import com.khedr.ShopVerse.data.model.home.Categories
import com.khedr.ShopVerse.data.model.home.HomeResponse
import com.khedr.ShopVerse.data.model.home.ProductByCategoriesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService  {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
    @GET("home")
    suspend fun getHomeData(): Response<HomeResponse>
    @GET("logout")
    suspend fun logout(): Response<String>
    @GET("categories")
    suspend fun getCategories(): Response<Categories>
    @GET("categories/{id}")
    suspend fun getCategoryById(@Path("id") id: String): Response<ProductByCategoriesResponse>
    @GET("favorites")
    suspend fun getFavorites(): Response<FavoriteResponse>
    @POST("favorites")
    suspend fun addOrDeleteFavorite(@Body id: AddOrDeleteRequest): Response<AddOrDeleteFavoriteResponse>
    @POST("products/search")
    suspend fun searchProduct(@Body searchQuery : SearchRequest): Response<SearchResponse>
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): Response<DetailsProductResponse>
    @GET("carts")
    suspend fun getCart(): Response<CartResponse>
    @POST("carts")
    suspend fun addProductToCart(@Body id: AddOrDeleteRequest): Response<AddToCartResponse>
    @POST("orders")
    suspend fun addOrders(@Body addOrderRequest: AddOrderRequest): Response<AddOrderResponse>
    @GET("profile")
    suspend fun getProfile(): Response<LoginResponse>
    @PUT("update-profile")
    suspend fun updateProfile(@Body updateProfileRequest: UpdateProfileRequest): Response<UpdateProfileResponse>






}