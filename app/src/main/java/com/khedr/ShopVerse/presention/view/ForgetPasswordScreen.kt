package com.khedr.ShopVerse.presention.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.khedr.ShopVerse.R
import com.khedr.ShopVerse.poppins

class ForgetPasswordScreen : Screen {
    @Composable
    override fun Content() {
        ForgetPasswordContent()
    }

    @Composable
    fun ForgetPasswordContent(){
        val navigator = LocalNavigator.currentOrThrow
        val email = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
                .windowInsetsPadding(WindowInsets.ime)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left),
                tint = colorResource(id = R.color.brown),
                contentDescription = null,modifier = Modifier
                    .align(Alignment.Start)
                    .clickable {
                        navigator.pop()
                    })
            Spacer(modifier = Modifier.height(8.dp))
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.key),
                tint = colorResource(id = R.color.brown),
                contentDescription = null,modifier = Modifier
                    .align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.forgot_password),
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth(),
                color = colorResource(id = R.color.brown),
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.plase_enter_your_email),
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.email),
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = colorResource(id = R.color.brown),
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                placeholder = { Text(text = stringResource(id = R.string.email_placeholder),
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )},
                modifier = Modifier
                    .height(52.dp)
                    .fillMaxWidth()
                    .background(Color.White),
                shape = RoundedCornerShape(30.dp),
                leadingIcon = {
                    Icon(
                        imageVector =ImageVector.vectorResource(id = R.drawable.email_ic),
                        contentDescription = null) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.brown),
                    unfocusedBorderColor = Color.Gray)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navigator.push(VerifyCodeScreen())

            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(30.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(colorResource(id = R.color.app_color))
            ) {
                Text(text = stringResource(id = R.string.reset_password),
                    textAlign = TextAlign.Center,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White)

            }
        }

    }
}