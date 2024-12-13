package com.khedr.ShopVerse.presention.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.khedr.ShopVerse.R



@Composable
fun LottieAnimationLoading( loading :Boolean = false) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_loading))
    val progress by animateLottieCompositionAsState(composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = loading
    )
    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            contentAlignment = Alignment.Center,
        ) {
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.size(150.dp),

            )
        }
    }
}

@Composable
fun LottieAnimationEmpty( loading :Boolean = false) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_empty))
    val progress by animateLottieCompositionAsState(composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = loading
    )
    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            contentAlignment = Alignment.Center,
        ) {
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.size(150.dp),

                )
        }
    }
}