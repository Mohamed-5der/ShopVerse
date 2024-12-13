package com.khedr.ShopVerse.presention.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.khedr.ShopVerse.R
import com.khedr.ShopVerse.data.model.Auth.LoginRequest
import com.khedr.ShopVerse.poppins
import com.khedr.ShopVerse.presention.viewModel.LoginViewModel
import com.khedr.ShopVerse.util.AppPreferences

class LoginScreen : Screen {
    lateinit var loginViewModel: LoginViewModel
    @Composable
    override fun Content() {
        loginViewModel = hiltViewModel()
        LoginContent()
    }
    @Composable
    fun LoginContent(){

        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize().verticalScroll(rememberScrollState())
                .background(Color.White).windowInsetsPadding(WindowInsets.ime)
                .padding(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.logo_shop),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(200.dp).align(Alignment.CenterHorizontally),
            )
            Text(text = stringResource(id = R.string.sign_in),
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth(),
                color = colorResource(id = R.color.brown),
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.hi_welcome_back),
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
            androidx.compose.material3.OutlinedTextField(
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
            Text(
                text = stringResource(id = R.string.pass),
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = colorResource(id = R.color.brown),
            )
            Spacer(modifier = Modifier.height(6.dp))
            androidx.compose.material3.OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                visualTransformation = PasswordVisualTransformation('*'),
                placeholder = { androidx.compose.material3.Text(text = stringResource(id = R.string.pass),
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Gray
                ) },
                shape = RoundedCornerShape(30.dp),
                leadingIcon = {
                    Icon(
                        imageVector =ImageVector.vectorResource(id = R.drawable.lock_ic),
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.brown),
                    unfocusedBorderColor = Color.Gray)
            )
            TextButton(onClick = {
                navigator.push(ForgetPasswordScreen())
            },Modifier.align(androidx.compose.ui.Alignment.End)) {
                Text(text = stringResource(id = R.string.forgot_password),
                    textAlign = TextAlign.End,
                    fontFamily = poppins,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.brown),
                    fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                login(email.value,password.value,context,navigator)
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(30.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(colorResource(id = R.color.app_color))
                ) {
                Text(text = stringResource(id = R.string.sign_in),
                    textAlign = TextAlign.Center,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White)

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center) {
                Text(text = stringResource(id = R.string.dont_have_account),
                    textAlign = TextAlign.Center,
                    fontFamily = poppins,
                    color = colorResource(id = R.color.brown),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,)
                TextButton(onClick = {
                    navigator.push(RegisterScreen())
                }) {
                    Text(text = stringResource(id = R.string.sign_up),
                        textAlign = TextAlign.Center,
                        fontFamily = poppins,
                        color = colorResource(id = R.color.app_color),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp)

                }
            }


        }


    }
    @Preview
    @Composable
    fun SimpleComposablePreview() {
        LoginContent()
    }
    fun login(email: String, password: String,context: Context,navigator: Navigator) {
        val loginRequest = LoginRequest(email, password)
        loginViewModel.login(loginRequest, onSuccess = { massage,token->
            Toast.makeText(context,massage, Toast.LENGTH_SHORT).show()
            navigator.push(HomeScreen())
        }, onError = {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        })

    }
}
