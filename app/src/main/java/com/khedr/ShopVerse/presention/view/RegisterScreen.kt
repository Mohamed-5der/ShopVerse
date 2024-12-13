package com.khedr.ShopVerse.presention.view

import android.content.Context
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.khedr.ShopVerse.R
import com.khedr.ShopVerse.data.model.Auth.RegisterRequest
import com.khedr.ShopVerse.poppins
import com.khedr.ShopVerse.presention.viewModel.RegisterViewModel
import com.khedr.ShopVerse.util.AppPreferences

class RegisterScreen :Screen {
    lateinit var registerViewModel: RegisterViewModel
    @Composable
    override fun Content() {
       RegisterContent()
    }
    @Composable
    fun RegisterContent(){
        val name = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val passwordConfirm = remember { mutableStateOf("") }
        val scrollState = rememberScrollState()
        val phone = remember { mutableStateOf("") }
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current
        registerViewModel = hiltViewModel()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(scrollState)
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
            Text(text = stringResource(id = R.string.create_account),
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth(),
                color = colorResource(id = R.color.brown),
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.fill_your_info),
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.name),
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = colorResource(id = R.color.brown),
            )
            Spacer(modifier = Modifier.height(6.dp))
            androidx.compose.material3.OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                placeholder = { Text(text = stringResource(id = R.string.name),
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
                },
                modifier = Modifier
                    .height(52.dp)
                    .fillMaxWidth()
                    .background(Color.White),
                shape = RoundedCornerShape(30.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.brown),
                    unfocusedBorderColor = Color.Gray)
            )
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

            Text(
                text = stringResource(id = R.string.phone_number),
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = colorResource(id = R.color.brown),
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = phone.value,
                onValueChange = { phone.value = it },
                placeholder = { androidx.compose.material3.Text(text = stringResource(id = R.string.phone_number),
                    color =Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(30.dp),
                leadingIcon = { Icon(imageVector = Icons.Outlined.Call, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White) ,
                colors =OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.brown),
                    unfocusedBorderColor = Color.Gray
                )
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
            OutlinedTextField(
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
                        imageVector = ImageVector.vectorResource(id = R.drawable.lock_ic),
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
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.confirm_pass),
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = colorResource(id = R.color.brown),
            )
            Spacer(modifier = Modifier.height(6.dp))
            androidx.compose.material3.OutlinedTextField(
                value = passwordConfirm.value,
                onValueChange = { passwordConfirm.value = it },
                placeholder = { androidx.compose.material3.Text(text = stringResource(id = R.string.confirm_pass),
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Gray
                ) },
                visualTransformation = PasswordVisualTransformation('*'),
                shape = RoundedCornerShape(30.dp),
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.lock_ic),
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
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (password.value ==passwordConfirm.value){
                    register(RegisterRequest(name = name.value, email = email.value, password = password.value, phone = phone.value),context)
                }else{
                    Toast.makeText(context, "password not match", Toast.LENGTH_SHORT).show()
                }

            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(30.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(colorResource(id = R.color.app_color))
            ) {
                Text(text = stringResource(id = R.string.sign_up),
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
                Text(text = stringResource(id = R.string.already_have_account),
                    textAlign = TextAlign.Center,
                    fontFamily = poppins,
                    color = colorResource(id = R.color.brown),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,)
                TextButton(onClick = {  }) {
                    Text(text = stringResource(id = R.string.sign_in),
                        textAlign = TextAlign.Center,
                        fontFamily = poppins,
                        color = colorResource(id = R.color.app_color),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp)

                }
            }


        }
    }
    fun register(registerRequest: RegisterRequest,context: Context){
        registerViewModel.register(registerRequest, onSuccess = {massage ,token->
            Toast.makeText(context, massage, Toast.LENGTH_SHORT).show()
        }, onError = {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }
}