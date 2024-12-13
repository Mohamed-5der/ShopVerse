package com.khedr.ShopVerse.presention.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khedr.ShopVerse.data.model.Auth.LoginRequest
import com.khedr.ShopVerse.data.model.Auth.LoginResponse
import com.khedr.ShopVerse.data.network.AuthInterceptor
import com.khedr.ShopVerse.data.repository.NetworkRepository
import com.khedr.ShopVerse.util.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val networkRepository: NetworkRepository
,private val authInterceptor: AuthInterceptor,
    private val appPreferences: AppPreferences) : ViewModel() {
    private var _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> get()  = _loginResponse

    fun login (requestLogin: LoginRequest, onSuccess: (String,String) -> Unit, onError: (String) -> Unit){
        viewModelScope.launch {
            try {
                val response = networkRepository.login(requestLogin)
                if (response.isSuccessful){
                    if (response.body()?.status == true){
                        _loginResponse.value = response.body()
                        onSuccess(loginResponse.value?.message?:"",loginResponse.value?.data?.token?:"")
                        appPreferences.init()
                        appPreferences.putString("token",loginResponse.value?.data?.token?:"")
                        authInterceptor.setToken(response.body()?.data?.token?:"")
                    }
                }else{
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error occurred"
                    Log.d("moh", "login: ${response.message()}")
                    onError(errorMessage)
                }
            }catch (e:Exception){
                Log.d("moh", "login: ${e.message}")
            }
        }
    }

}