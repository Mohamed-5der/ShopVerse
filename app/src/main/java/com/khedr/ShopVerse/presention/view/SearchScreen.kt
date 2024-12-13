package com.khedr.ShopVerse.presention.view

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.khedr.ShopVerse.R
import com.khedr.ShopVerse.data.model.FavoriteItem
import com.khedr.ShopVerse.data.model.Product
import com.khedr.ShopVerse.poppins
import com.khedr.ShopVerse.presention.viewModel.FavoriteViewModel
import com.khedr.ShopVerse.presention.viewModel.SearchViewModel
import kotlin.random.Random

class SearchScreen :Screen {
    lateinit var favoriteViewModel: FavoriteViewModel
    lateinit var searchViewModel: SearchViewModel
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    override fun Content() {
        favoriteViewModel = hiltViewModel()
        searchViewModel = hiltViewModel()

        Scaffold(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, bottom = 24.dp)
                .fillMaxSize()
                .background(Color.White),
            topBar = {
                TopAppBar(
                    elevation = 0.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    backgroundColor = colorResource(id = R.color.white),
                    title = { Text(
                        stringResource(id = R.string.search),
                        fontFamily = poppins,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.brown),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
                        textAlign = TextAlign.Center) })
            }
        ) {innerPadding->
            SearchContent()
        }
    }
    @Composable
   fun SearchContent(){
        val searchText = remember { mutableStateOf("") }
        Column (modifier = Modifier
            .fillMaxSize()){
            Spacer(modifier = Modifier.height(16.dp))
            Row (horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = searchText.value, onValueChange = { searchText.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)

                        .border(
                            1.dp,
                            colorResource(id = R.color.brown),
                            RoundedCornerShape(30.dp)
                        ),
                    leadingIcon = {
                        IconButton(onClick = {
                        }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search",)
                        }

                    },
                    placeholder = { androidx.compose.material3.Text(text = stringResource(id = R.string.search),
                        modifier = Modifier.clickable {
                        }) },
                    shape = RoundedCornerShape(30.dp),
                    colors = OutlinedTextFieldDefaults.colors(colorResource(id = R.color.brown))
                )

            }
                SearchContentTwo(searchText.value)
        }
    }
    @Composable
    fun SearchContentOne(){
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Recent",)
                TextButton(onClick = {

                }) {
                    Text(text = "Clear all",
                        color = colorResource(id = R.color.app_color))
                }
            }
            Divider( modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
                color = colorResource(id = R.color.scoung),
                thickness = 0.7.dp)
            LazyColumn {
                items(5) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "item",)
                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Filled.AddCircle,
                                contentDescription = "Clear item"
                            )
                        }
                    }
                }
            }
        }



    }
    @Composable
    fun SearchContentTwo(search:String){
        searchViewModel.search(search)
        val searchProduct = searchViewModel.searchResponse.collectAsState().value?.data?.products

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Recent",)
                TextButton(onClick = {

                }) {
                    Text(text = "Clear all",
                        color = colorResource(id = R.color.app_color))
                }
            }
            Divider( modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
                color = colorResource(id = R.color.scoung),
                thickness = 0.7.dp)
            GetFavoriteProducts(searchProduct)
        }
    }

    @Composable
    fun GetFavoriteProducts(products: List<Product>?){
        if (products?.size ?: null == null){
            Text(text = "No products found")
        }else{
            Column (modifier = Modifier.fillMaxWidth()){
                Spacer(modifier = Modifier.height(24.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(products ?: emptyList()) { product ->
                        ProductCard(product = product)
                    }
                }
            }
        }


    }
    @Composable
    fun ProductCard(product: Product) {
        val rating = Random.nextInt(3, 5)
        androidx.compose.material3.Card(
            modifier = Modifier
                .width(200.dp)
                .padding(top = 8.dp, start = 4.dp, end = 4.dp, bottom = 8.dp)
                .wrapContentHeight()
                .clickable {

                },
            colors = CardDefaults.cardColors(colorResource(id = R.color.white)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = RoundedCornerShape(12.dp),
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    androidx.compose.material3.Card(
                        colors = CardDefaults.cardColors(colorResource(id = R.color.white)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Fit,
                            placeholder = painterResource(id = R.drawable.logo_shop),
                            error = painterResource(id = R.drawable.logo_shop)
                        )
                    }

                    if (product.discount != null && product.discount ?: 0 > 0) {
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .width(40.dp)
                                .height(20.dp)
                                .clip(CircleShape)
                                .background(Color.Red)
                                .align(Alignment.TopStart)
                        ) {
                            androidx.compose.material3.Text(
                                text = "${product.discount}%",
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = colorResource(id = R.color.white),
                                fontFamily = poppins,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (product.oldPrice == product.price) {

                            androidx.compose.material3.Text(
                                text = "£${product.oldPrice}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Start,
                                color = colorResource(id = R.color.brown),
                                fontFamily = poppins,
                                modifier = Modifier.fillMaxWidth()
                            )
                        } else {

                            androidx.compose.material3.Text(
                                text = "£${product.oldPrice}",
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = Color.Gray,
                                textDecoration = TextDecoration.LineThrough,
                                fontFamily = poppins,
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            androidx.compose.material3.Text(
                                text = "£${product.price}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = colorResource(id = R.color.brown),
                                fontFamily = poppins,
                            )
                        }
                    }

                    Icon(
                        painterResource(
                            id = R.drawable.heart_se
                        ),
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(24.dp)
                            .clickable {
                                favoriteViewModel.addOrDeleteFavorite(product.id.toString(), {})

                            },
                        tint = colorResource(id = R.color.app_color)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                androidx.compose.material3.Text(
                    text = product.name ?: "",
                    fontSize = 14.sp,
                    minLines = 2,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.gray),
                    fontFamily = poppins,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val brand = product.name?.split(" ")?.getOrNull(0) ?: "Unknown"
                    androidx.compose.material3.Text(
                        text = "From: $brand",
                        fontSize = 14.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        modifier = Modifier
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(18.dp)
                        )
                        androidx.compose.material3.Text(
                            text = "$rating",
                            fontSize = 14.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
        }
    }


}