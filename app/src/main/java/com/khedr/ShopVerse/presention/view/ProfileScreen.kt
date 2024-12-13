package com.khedr.ShopVerse.presention.view

import android.net.Uri
import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.khedr.ShopVerse.R
import com.khedr.ShopVerse.data.model.Auth.UpdateProfileRequest
import com.khedr.ShopVerse.poppins
import com.khedr.ShopVerse.presention.viewModel.ProfileViewModel
import java.io.ByteArrayOutputStream

class ProfileScreen :Screen {
    lateinit var profileViewModel :ProfileViewModel
    lateinit var navigator: Navigator
    @Composable
    override fun Content() {
         navigator = LocalNavigator.currentOrThrow
        profileViewModel = hiltViewModel()
        val profileData = profileViewModel.profileData.collectAsState().value
        val context = LocalContext.current
        var selectedImageUri = remember { mutableStateOf<Uri?>(null) }
        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            selectedImageUri.value = uri
            if (selectedImageUri.value != null) {
                val imageBase64 = selectedImageUri.value?.let { uri ->
                    val inputStream = context.contentResolver.openInputStream(uri)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    inputStream?.copyTo(byteArrayOutputStream)
                    inputStream?.close()
                    Base64.encodeToString(
                        byteArrayOutputStream.toByteArray(),
                        Base64.DEFAULT
                    )
                }
                val updateProfileRequest = UpdateProfileRequest(
                    name = profileData?.data?.name ?: "Mohamed Khedr",
                    email = profileData?.data?.email ?: "khedrkhedr370@gmail.com",
                    phone = profileData?.data?.phone ?: "01203898109",
                    image = imageBase64 ?: ""
                )
                // Send request through ViewModel
                profileViewModel.updateProfile(updateProfileRequest) {
                    Toast
                        .makeText(context, it, Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast
                    .makeText(context, "Please select an image", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(contentAlignment = Alignment.BottomEnd) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data( profileData?.data?.image ?: R.drawable.image)
                        .placeholder(R.drawable.user_se) // Shown while loading
                        .error(R.drawable.user_se)       // Shown if there's an error loading the image
                        .crossfade(true)                 // Adds a smooth crossfade effect
                        .build(),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.app_color),
                            shape = CircleShape
                        )
                        .padding(4.dp), // Optional: inner padding for border spacing
                    contentScale = ContentScale.Crop
                )

                Card(
                    modifier = Modifier
                        .background(color = colorResource(id = R.color.app_color), CircleShape)
                        .border(1.dp, Color.White, CircleShape)
                        .size(40.dp)
                        .clickable {
                            galleryLauncher.launch("image/*")
                        },
                    shape = CircleShape,
                    colorResource(id = R.color.app_color)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        modifier = Modifier.padding(8.dp),
                        tint = Color.White
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = profileData?.data?.name?:"",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                fontFamily = poppins,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            ProfileOptionItem(
                icon = ImageVector.vectorResource(id = R.drawable.user_se),
                label = stringResource(id = R.string.profile),
                function = ProfileScreen()
            )
            ProfileOptionItem(
                icon = Icons.Default.ShoppingCart,
                label = stringResource(id = R.string.order),
            )
            ProfileOptionItem(
                icon = ImageVector.vectorResource(id = R.drawable.cart_se),
                label = stringResource(id = R.string.coupon),
            )
            ProfileOptionItem(
                icon = Icons.Default.Settings,
                label = stringResource(id = R.string.setting),
            )
            ProfileOptionItem(
                    icon = Icons.Default.ExitToApp,
            label = stringResource(id = R.string.logout),
            )


        }
    }
    @Composable
    fun ProfileOptionItem(
        icon: ImageVector,
        label: String,
        function : Screen = ProfileScreen()

    ) {
        val context = LocalContext.current
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .clickable {
                    navigator.push(function)

                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = colorResource(id = R.color.app_color),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = label, fontSize = 20.sp,
                color = colorResource(id = R.color.brown),
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = label,
                tint = colorResource(id = R.color.app_color),
                modifier = Modifier.size(24.dp)
            )
        }
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.alpha(0.6f),
        )
        Spacer(modifier = Modifier.height(8.dp))
    }

}