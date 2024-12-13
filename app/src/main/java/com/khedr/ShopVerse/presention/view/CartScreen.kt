package com.khedr.ShopVerse.presention.view

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.khedr.ShopVerse.R
import com.khedr.ShopVerse.data.model.CartResponse
import com.khedr.ShopVerse.data.model.home.Products
import com.khedr.ShopVerse.poppins
import com.khedr.ShopVerse.presention.viewModel.CartViewModel
import com.khedr.ShopVerse.util.SwipeToDeleteContainer

class CartScreen:Screen {
    lateinit var cartViewModel: CartViewModel
     var product:CartResponse? = null
      lateinit var context : Context

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    override fun Content() {
        cartViewModel = hiltViewModel()
        cartViewModel.getCart()
        context = LocalContext.current

        product = cartViewModel.cartResponse.collectAsState().value
        Scaffold(
            topBar = {
                TopAppBar(
                    elevation = 0.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    backgroundColor = colorResource(id = R.color.white),
                    title = { Text(
                        stringResource(id = R.string.cart),
                        fontFamily = poppins,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.brown),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
                        textAlign = TextAlign.Center) })
            }
        ) {

            ProductItemCard()
        }
    }

    @Composable
    fun ProductItemCard() {
        val loading = cartViewModel.isLoading.collectAsState().value

        Column (horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){
            Spacer(modifier = Modifier.height(12.dp))
            if (loading){
                LottieAnimationLoading(loading)
            }else{
                LazyColumn(
                    modifier = Modifier.heightIn(0.dp, 1000.dp)
                ) {
                    items(product?.data?.cartItems ?: emptyList()) { cartItem ->
                        val numberOfItems = remember { mutableStateOf(1) }

                        // Wrapping each item in SwipeToDeleteContainer
                        SwipeToDeleteContainer(
                            item = cartItem,
                            product = cartItem.product,
                            onDelete = { deletedItem ->
                                cartViewModel.addOrDeleteProductToCart(deletedItem.product.id.toString()){
                                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                                }
                            }
                        ) { item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .padding(horizontal = 8.dp, vertical = 8.dp),
                                elevation = 4.dp,
                                shape = RoundedCornerShape(8.dp),
                                backgroundColor = Color.White
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .background(Color.White),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    AsyncImage(
                                        model = item.product.image,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(RoundedCornerShape(8.dp)),
                                        contentScale = ContentScale.Fit,
                                        placeholder = painterResource(id = R.drawable.logo_shop),
                                        error = painterResource(id = R.drawable.logo_shop)
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Column(
                                        modifier = Modifier.weight(1f),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        val name = item.product.name?.split(" ")
                                        Text(
                                            text = name?.take(2)?.joinToString(" ") ?: "",
                                            fontFamily = poppins,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 18.sp,
                                            color = Color.Black
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "$" + item.product.price.toString(),
                                            color = Color.Gray,
                                            fontFamily = poppins,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 18.sp
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Card {
                                            IconButton(
                                                onClick = {
                                                    if (numberOfItems.value > 1) {
                                                        numberOfItems.value--
                                                    }
                                                },
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .background(Color.LightGray, shape = RectangleShape)
                                            ) {
                                                Text(
                                                    text = "-",
                                                    fontFamily = poppins,
                                                    fontSize = 18.sp,
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .align(Alignment.CenterVertically),
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        }
                                        Text(
                                            text = numberOfItems.value.toString(),
                                            modifier = Modifier.padding(horizontal = 8.dp),
                                            fontFamily = poppins,
                                            color = Color.Black,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 16.sp
                                        )
                                        Card {
                                            IconButton(
                                                onClick = { numberOfItems.value++ },
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .background(
                                                        Color(0xFF8B4513),
                                                        shape = RectangleShape
                                                    )
                                            ) {
                                                Icon(
                                                    Icons.Default.Add,
                                                    contentDescription = "Increase",
                                                    tint = Color.White,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (product?.data?.cartItems?.isEmpty() == true) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.cart),
                            fontFamily = poppins,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,)
                    }
                }else{
                    androidx.compose.material3.Button(
                        onClick = {
                                cartViewModel.addOrder {
                                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                                }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp).padding(horizontal = 8.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(colorResource(id = R.color.app_color))
                    ) {
                        Text(
                            text = stringResource(id = R.string.checkout),
                            textAlign = TextAlign.Center,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(100.dp))
                }


            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CreateBottomSheet(isSheetOpen: MutableState<Boolean>, onCancel: () -> Unit, onRemove: () -> Unit, product: Products) {

        if (isSheetOpen.value) {
            val sheetState = androidx.compose.material3.rememberModalBottomSheetState()
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    isSheetOpen.value = false
                },
                modifier = Modifier.height(300.dp),
                containerColor = Color.White
            ) {


            }
        }
    }

}