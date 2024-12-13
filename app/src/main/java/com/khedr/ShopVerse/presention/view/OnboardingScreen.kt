package com.khedr.ShopVerse.presention.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.khedr.ShopVerse.R
import com.khedr.ShopVerse.data.model.home.OnboardingPage
import com.khedr.ShopVerse.poppins
import kotlinx.coroutines.launch

class OnboardingScreen : Screen {
    @Composable
    override fun Content() {
        OnboardingContent()
    }
    @Composable
    fun OnboardingContent() {
        val pages = listOf(
            OnboardingPage(R.drawable.onboarding1, "Seamless Shopping Experience", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
            OnboardingPage(R.drawable.onboarding2, "Wishlist: Where Fashion Dreams Begin", "Lorem ipsum dolor sit amet, sed do eiusmod tempor."),
            OnboardingPage(R.drawable.onboarding3, "Swift and Reliable Delivery", "Lorem ipsum dolor sit amet, incididunt ut labore.")
        )

        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                if (pagerState.currentPage != 2) {
                    TextButton(onClick = {

                    }) {
                        Text("Skip", color = colorResource(id = R.color.app_color), fontFamily = poppins,
                            fontWeight = FontWeight.Normal, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }else{
                    Spacer(modifier = Modifier.height(50.dp))
                }

            }

            // Pager for onboarding pages
            HorizontalPager(
                count = pages.size,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingPageScreen(pages[page])
            }

            // Page indicator and next button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (pagerState.currentPage > 0) {
                    FloatingActionButton(modifier = Modifier.size(44.dp).
                    border(shape = CircleShape, width = 1.dp, color = colorResource(id = R.color.app_color)),
                        backgroundColor = Color.White,
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                        onClick = {
                        scope.launch {
                            pagerState.scrollToPage(pagerState.currentPage - 1)
                        }
                    }) {
                        Icon(
                            tint = colorResource(id = R.color.app_color),
                            imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left),
                            contentDescription = "Back"
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(44.dp))
                }

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = colorResource(id = R.color.app_color),
                    inactiveColor = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                FloatingActionButton(backgroundColor = colorResource(id = R.color.app_color),
                    modifier = Modifier.size(50.dp),
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),onClick = {
                    if (pagerState.currentPage == pages.size - 1) {
                        //onFinish() // Last page, finish onboarding
                    } else {
                        scope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }) {
                    Icon(
                        tint = Color.White,
                        imageVector = ImageVector.vectorResource(id = R.drawable.arrow_right),
                        contentDescription = "Next"
                    )
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    @Composable
    fun OnboardingPageScreen(page: OnboardingPage) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(page.imageRes),
                contentDescription = page.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                text = page.title,
                modifier = Modifier.padding(horizontal = 8.dp),
                style = TextStyle(fontSize = 27.sp,
                    color = colorResource(id = R.color.brown),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)
                    ,fontFamily = poppins
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = page.description,
                modifier = Modifier.padding(horizontal = 8.dp),color = Color.Gray,
                fontWeight = FontWeight.Medium ,
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontSize = 14.sp
            )
        }
    }
}