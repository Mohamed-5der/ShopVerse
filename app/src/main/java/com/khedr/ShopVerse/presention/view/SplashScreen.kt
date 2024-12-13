package com.khedr.ShopVerse.presention.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.khedr.ShopVerse.R
import com.khedr.ShopVerse.data.network.AuthInterceptor
import com.khedr.ShopVerse.util.AppPreferences
import kotlinx.coroutines.delay
import java.util.Locale
import javax.inject.Inject

class SplashScreen :Screen  {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val appPreferences = AppPreferences(LocalContext.current)
        appPreferences.init()
        LaunchedEffect(key1 = true) {
            delay(1000)
            try {
                if (appPreferences.getString("token","")!=""){
                    navigator.push(HomeScreen())
                }else {
                    navigator.push(LoginScreen())
                }
            }catch (e:Exception){
                Log.d("TAG", "Splash: ${e.message}")

            }

        }
        Box(modifier = Modifier.fillMaxSize().background(Color.White),
            contentAlignment = Alignment.Center){
            Image(painter = painterResource(id = R.drawable.logo_shop),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth)
        }
    }

}
