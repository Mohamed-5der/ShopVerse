package com.khedr.ShopVerse.presention.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khedr.ShopVerse.data.model.Auth.LoginRequest
import com.khedr.ShopVerse.data.model.Auth.LoginResponse
import com.khedr.ShopVerse.data.model.Auth.RegisterRequest
import com.khedr.ShopVerse.data.model.Auth.RegisterResponse
import com.khedr.ShopVerse.data.network.AuthInterceptor
import com.khedr.ShopVerse.data.repository.NetworkRepository
import com.khedr.ShopVerse.util.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RegisterViewModel @Inject constructor(private val networkRepository: NetworkRepository,
    val authInterceptor: AuthInterceptor,val appPreferences: AppPreferences
) : ViewModel() {
    private var _registerResponse = MutableStateFlow<RegisterResponse?>(null)
    val registerResponse: StateFlow<RegisterResponse?> get()  = _registerResponse

    fun register (requestRegister: RegisterRequest, onSuccess: (String,String) -> Unit, onError: (String) -> Unit){
        viewModelScope.launch {
            try {
                val response = networkRepository.register(requestRegister)
                if (response.isSuccessful){
                    _registerResponse.value = response.body()
                    onSuccess(registerResponse.value?.message?:"empty message",registerResponse.value?.data?.token?:"")
                    appPreferences.init()
                    appPreferences.putString("token",registerResponse.value?.data?.token?:"")
                    authInterceptor.setToken(response.body()?.data?.token?:"")
                }else{
                    Log.d("moh", "register: ${response.message()}")
                    onError(response.body()?.message?:"")
                }
            }catch (e:Exception){
                Log.d("moh", "register: ${e.message}")
            }
        }
    }
}