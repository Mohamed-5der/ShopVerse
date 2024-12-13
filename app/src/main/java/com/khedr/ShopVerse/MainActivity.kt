package com.khedr.ShopVerse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import com.khedr.ShopVerse.data.network.AuthInterceptor
import com.khedr.ShopVerse.presention.view.DetailsProductScreen
import com.khedr.ShopVerse.presention.view.HomeScreen
import com.khedr.ShopVerse.presention.view.LoginScreen
import com.khedr.ShopVerse.presention.view.OnboardingScreen
import com.khedr.ShopVerse.presention.view.SplashScreen
import com.khedr.ShopVerse.ui.theme.ShopVerseTheme
import com.khedr.ShopVerse.util.AppPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val appPreferences = AppPreferences(this)
        appPreferences.init()
        setContent {
           Navigator(screen = SplashScreen())
        }
    }
}
val poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.ExtraBold),
)
