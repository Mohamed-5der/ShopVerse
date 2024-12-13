package com.khedr.ShopVerse.presention.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.khedr.ShopVerse.R
import com.khedr.ShopVerse.data.model.ProductData
import com.khedr.ShopVerse.data.model.home.Products
import com.khedr.ShopVerse.poppins
import com.khedr.ShopVerse.presention.viewModel.CartViewModel
import com.khedr.ShopVerse.presention.viewModel.DetailsProductViewModel

class DetailsProductScreen(val id:String) : Screen {
    lateinit var detailsProductViewModel: DetailsProductViewModel
    lateinit var cartViewModel: CartViewModel
    lateinit var navigator: Navigator
    @Composable
    override fun Content() {
        detailsProductViewModel= hiltViewModel()
        detailsProductViewModel.getProductById(id)
        cartViewModel = hiltViewModel()
        navigator = LocalNavigator.currentOrThrow
        DetailsProductContent()
    }
    @Composable
    fun DetailsProductContent(){
       val isLoading = detailsProductViewModel.isLoading.collectAsState().value
        val detailsProduct = detailsProductViewModel.detailsProductResponse.collectAsState().value
        val context = LocalContext.current
        Box (modifier = Modifier.fillMaxSize()){
            if (isLoading) {
                Box (modifier = Modifier.fillMaxSize().align(Alignment.Center)){
                    LottieAnimationLoading(isLoading)
                }
            }else{
                Column {
                    Box {
                        ImagePager(detailsProduct?.data)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 50.dp)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.arrow_left),
                                contentDescription = "Back",
                                modifier = Modifier.size(30.dp).clickable {
                                    navigator.pop()
                                },
                                tint = colorResource(id = R.color.black)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                painterResource(
                                    id = if (detailsProduct?.data?.inFavorites?:false==false) R.drawable.heart_un else R.drawable.heart_se
                                ),
                                contentDescription = "Favorite",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable {

                                    },
                                tint = if (true) colorResource(id = R.color.app_color) else Color.Gray
                            )

                        }
                    }
                    AddContent(product = detailsProduct?.data)
                }
                Card(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(92.dp)
                    .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(Color.White),) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp, vertical = 18.dp)
                    ) {
                        Column {
                            Text(
                                text = stringResource(id = R.string.total_price),
                                fontSize = 18.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black,
                                modifier = Modifier
                            )
                            Text(
                                text ="£"+detailsProduct?.data?.price.toString(),
                                fontSize = 18.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black,
                            )
                        }

                        Button(onClick = {
                            cartViewModel.addOrDeleteProductToCart(id) {
                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            }

                        },
                            modifier = Modifier
                                .height(52.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(colorResource(id = R.color.app_color))
                        ) {
                            Row (
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 24.dp, vertical =0.dp)
                            ){
                                Text(text = stringResource(id = R.string.add_to_cart),
                                    textAlign = TextAlign.Center,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.White)
                                Icon(painter = painterResource(id = R.drawable.cart_un), contentDescription =null,
                                    modifier = Modifier.size(30.dp).padding(start = 8.dp),)
                            }


                        }
                    }
                }
            }
        }
    }
    @Composable
    fun ImagePager( product : ProductData?) {
        val pagerState = rememberPagerState()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
        ) {
            HorizontalPager(
                count = product?.images?.size?:1,
                state = pagerState,
            ) { page ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    shape = RoundedCornerShape(0.dp),
                    colors = CardDefaults.cardColors(colorResource(id = R.color.scoung))
                ) {
                    Box(modifier = Modifier
                        .height(340.dp)
                        .fillMaxWidth()
                        .clip(RectangleShape)
                        .background(
                            colorResource(id = R.color.white)
                        )
                    ) {
                        AsyncImage(
                            model = product?.images?.get(page),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Fit,
                            placeholder = painterResource(id = R.drawable.logo_shop),
                            error = painterResource(id = R.drawable.logo_shop)
                        )
                    }
                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 120.dp),
                activeColor = colorResource(id = R.color.app_color),
                indicatorWidth = 10.dp,

                )
            Column (modifier = Modifier.align(Alignment.BottomCenter)){
                CardPrice(product)
            }

        }
    }
    @Composable
    fun CardPrice(product: ProductData?){

        Column {
            Card(modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .height(92.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                ){

                Column(modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val name = product?.name?.split(" ")
                        Text(
                            text = name?.take(2)?.joinToString(" ")?:"",
                            fontSize = 14.sp,
                            fontFamily = poppins,
                            maxLines = 1,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = product?.price.toString()+"£",
                            fontSize = 20.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )

                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Rating",
                                tint = Color(0xFFFFD700),
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "4.6 (102k Reviews)",
                                fontSize = 14.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                            )
                        }
                        if (product?.discount != 0){
                            Text(
                                text = product?.oldPrice.toString()+"£"?:"",
                                fontSize = 16.sp,
                                fontFamily = poppins,
                                textDecoration = TextDecoration.LineThrough,
                                fontWeight = FontWeight.Medium,
                                color = Color.LightGray,
                            )
                        }


                    }
                }

            }
        }

    }
    @Composable
    fun AddContent(product: ProductData?){
        var expanded = remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.description),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            val descriptionToShow = if (expanded.value) product?.description ?: "" else {
                product?.description?.take(100)
            }

            Text(
                text = buildAnnotatedString {
                    append(descriptionToShow)
                    if (!expanded.value) {
                        append(" ")
                        withStyle(style = SpanStyle(
                            color = colorResource(id = R.color.app_color),
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                        )
                        ) {
                            append("...."+stringResource(id = R.string.read_more))
                        }
                    }
                },
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                lineHeight = 20.sp,
                modifier = Modifier.clickable {
                    expanded.value = !expanded.value
                })
        }

    }

}