package com.khedr.ShopVerse.presention.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khedr.ShopVerse.data.model.Auth.LoginResponse
import com.khedr.ShopVerse.data.model.Auth.UpdateProfileRequest
import com.khedr.ShopVerse.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val repository: NetworkRepository):ViewModel() {
    private  val _profileData = MutableStateFlow<LoginResponse?>(null)
    val profileData: StateFlow<LoginResponse?> get() = _profileData
    init {
        getProfile()
    }
     fun getProfile(){
          viewModelScope.launch {
              try {
                  val response = repository.getProfile()
                  if (response.isSuccessful){
                      _profileData.value = response.body()
                  }
              }catch (e:Exception){
                  Log.d("moh", "getProfile: ${e.message}")
              }

          }
     }
    fun updateProfile(
        updateProfileRequest: UpdateProfileRequest,
        onSuccess:(String?)->Unit
    ){
        viewModelScope.launch {
            try {
                val response = repository.updateProfile(updateProfileRequest)
                if (response.isSuccessful){
                    onSuccess(response.body()?.message)
                    getProfile()
                }
            }catch (e:Exception){
                Log.d("moh", "getProfile: ${e.message}")
            }

        }
    }


}