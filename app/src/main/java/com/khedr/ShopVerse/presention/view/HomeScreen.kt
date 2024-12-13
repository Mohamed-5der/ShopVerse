package com.khedr.ShopVerse.presention.view

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.khedr.ShopVerse.R
import com.khedr.ShopVerse.data.model.home.Categories
import com.khedr.ShopVerse.data.model.home.Category
import com.khedr.ShopVerse.data.model.home.Products
import com.khedr.ShopVerse.poppins
import com.khedr.ShopVerse.presention.viewModel.DetailsProductViewModel
import com.khedr.ShopVerse.presention.viewModel.FavoriteViewModel
import com.khedr.ShopVerse.presention.viewModel.HomeViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeScreen :Screen {
    lateinit var homeViewModel: HomeViewModel
    lateinit var favoriteViewModel: FavoriteViewModel
    lateinit var detailsProductViewModel: DetailsProductViewModel
     lateinit var id: MutableState<Int>
     lateinit var navigator: Navigator
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
    @Composable
    override fun Content() {
        homeViewModel= hiltViewModel()
        favoriteViewModel= hiltViewModel()
        detailsProductViewModel= hiltViewModel()
        navigator = LocalNavigator.currentOrThrow
        id = remember { mutableStateOf(0) }
        var products by remember { mutableStateOf<List<Products>>(emptyList()) }
        products = homeViewModel.homeResponse.collectAsState().value?.data?.products ?: emptyList()
        val productsByCategory = homeViewModel.categoriesById.collectAsState().value?.data?.items
        if (productsByCategory != null) {
                products = productsByCategory
        }

        val selectedTabIndex = remember { mutableStateOf(0) }
        Column (modifier = Modifier
            .fillMaxSize()
            .background(Color.White)){
            Scaffold (
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 24.dp)
                    .fillMaxSize()
                    .background(Color.White),
                bottomBar = {
                    Column (modifier = Modifier
                        .fillMaxWidth()){
                        BottomNavigation(
                            backgroundColor = colorResource(id = R.color.navy_blue),
                            contentColor = Color.White,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .height(62.dp),

                            ) {
                            Row (horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment =Alignment.CenterVertically ){
                                BottomNavigationItem(
                                    icon = {
                                        if (selectedTabIndex.value == 0) {
                                            Card(modifier = Modifier.size(48.dp),
                                                shape = CircleShape,
                                                colors = CardDefaults.cardColors(Color.White)
                                            ) {
                                                Box(modifier = Modifier.fillMaxSize(),){
                                                    Icon(
                                                        painterResource(
                                                            id = R.drawable.home_se
                                                        ),
                                                        contentDescription = "home",
                                                        modifier = Modifier
                                                            .size(30.dp)
                                                            .align(Alignment.Center),
                                                        tint = colorResource(id = R.color.app_color)
                                                    )
                                                }
                                            }
                                        }else{
                                            Icon(
                                                painterResource(
                                                    id = R.drawable.home_un
                                                ),
                                                contentDescription = "home",
                                                modifier = Modifier.size(30.dp),
                                                tint = colorResource(id = R.color.white)
                                            )
                                        }
                                    },
                                    selected = selectedTabIndex.value == 0,
                                    onClick = { selectedTabIndex.value = 0}
                                )
                                BottomNavigationItem(
                                    icon = {
                                        if (selectedTabIndex.value == 1) {
                                            Card(modifier = Modifier.size(48.dp),
                                                shape = CircleShape,
                                                colors = CardDefaults.cardColors(Color.White)
                                            ) {
                                                Box(modifier = Modifier.fillMaxSize(),){
                                                    Icon(
                                                        painterResource(
                                                            id = R.drawable.cart_se
                                                        )
                                                        ,
                                                        contentDescription = "cart",
                                                        modifier = Modifier
                                                            .size(30.dp)
                                                            .align(Alignment.Center),
                                                        tint = colorResource(id = R.color.app_color)
                                                    )
                                                }
                                            }
                                        }else{
                                            Icon(
                                                painterResource(
                                                    id = R.drawable.cart_un
                                                ),
                                                contentDescription = "cart",
                                                modifier = Modifier.size(30.dp),
                                                tint = colorResource(id = R.color.white)
                                            )
                                        }
                                    },
                                    selected = selectedTabIndex.value == 1,
                                    onClick = { selectedTabIndex.value = 1 }
                                )

                                BottomNavigationItem(
                                    icon = {
                                        if (selectedTabIndex.value == 2) {
                                            Card(modifier = Modifier.size(48.dp),
                                                shape = CircleShape,
                                                colors = CardDefaults.cardColors(Color.White)
                                            ) {
                                                Box(modifier = Modifier.fillMaxSize(),){
                                                    Icon(
                                                        painterResource(
                                                            id = R.drawable.heart_se
                                                        ),
                                                        contentDescription = "favorite",
                                                        modifier = Modifier
                                                            .size(30.dp)
                                                            .align(Alignment.Center),
                                                        tint = colorResource(id = R.color.app_color)
                                                    )
                                                }
                                            }
                                        }else{
                                            Icon(
                                                painterResource(
                                                    id =R.drawable.heart_un
                                                ),
                                                contentDescription = "favorite",
                                                modifier = Modifier.size(30.dp),
                                                tint = colorResource(id = R.color.white)
                                            )
                                        }
                                    },
                                    selected = selectedTabIndex.value == 2,
                                    onClick = { selectedTabIndex.value = 2 }
                                )

                                BottomNavigationItem(
                                    icon = {
                                        if (selectedTabIndex.value == 3) {
                                            Card(modifier = Modifier.size(48.dp),
                                                shape = CircleShape,
                                                colors = CardDefaults.cardColors(Color.White)
                                            ) {
                                                Box(modifier = Modifier.fillMaxSize(),){
                                                    Icon(
                                                        painterResource(
                                                            id = R.drawable.search_se
                                                        )
                                                        ,
                                                        tint = colorResource(id = R.color.app_color),
                                                        contentDescription = "search",
                                                        modifier = Modifier
                                                            .size(30.dp)
                                                            .align(Alignment.Center)
                                                    )

                                                }
                                            }
                                        }else{
                                            Icon(
                                                painterResource(
                                                    id = R.drawable.search_un
                                                ),
                                                contentDescription = "search",
                                                modifier = Modifier.size(30.dp),
                                                tint = colorResource(id = R.color.white)
                                            )
                                        }
                                    },
                                    selected = selectedTabIndex.value == 3,
                                    onClick = { selectedTabIndex.value = 3 }
                                )
                                BottomNavigationItem(
                                    icon = {
                                        if (selectedTabIndex.value == 4) {
                                            Card(modifier = Modifier.size(48.dp),
                                                shape = CircleShape,
                                                colors = CardDefaults.cardColors(Color.White)
                                            ) {
                                                Box(modifier = Modifier.fillMaxSize(),){
                                                    Icon(
                                                        painterResource(
                                                            id = R.drawable.user_se
                                                        ),
                                                        contentDescription = "user",
                                                        tint = colorResource(id = R.color.app_color),
                                                        modifier = Modifier
                                                            .size(30.dp)
                                                            .align(Alignment.Center)
                                                    )
                                                }
                                            }
                                        }else{
                                            Icon(
                                                painterResource(
                                                    id = R.drawable.user_un
                                                ),
                                                contentDescription = "user",
                                                modifier = Modifier.size(30.dp),
                                                tint = colorResource(id = R.color.white)
                                            )
                                        }
                                    },
                                    selected = selectedTabIndex.value == 4,
                                    onClick = { selectedTabIndex.value = 4 }
                                )
                            }

                        }
                    }

                },


            ){innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(top = 50.dp) // Apply innerPadding to the Column
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    when (selectedTabIndex.value) {
                        0 -> HomeScreenContent(products)
                        1 -> CartScreen().Content()
                        2 -> FavoriteScreen().Content()
                        3 -> SearchScreen().Content()
                        4 -> ProfileScreen().Content()
                    }
                }
            }
        }
    }
    @Composable
    fun HomeScreenContent(product: List<Products>){
        val searchText = remember { mutableStateOf("") }
        val isLoading = homeViewModel.isLoading.collectAsState().value
        Box {
            Column (modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)){
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Row (horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        OutlinedTextField(
                            value = searchText.value, onValueChange = { searchText.value = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(4f)
                                .height(50.dp)
                                .border(
                                    1.dp,
                                    colorResource(id = R.color.brown),
                                    RoundedCornerShape(30.dp)
                                ),
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                            },
                            placeholder = { Text(text = stringResource(id = R.string.search)) },
                            shape = RoundedCornerShape(30.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = colorResource(id = R.color.brown),
                                unfocusedBorderColor = Color.Gray)

                        )
                        Box(modifier = Modifier
                            .weight(0.8f)
                            .clip(CircleShape))
                        {
                            Card(modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.Center),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(colorResource(id = R.color.app_color))
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {

                                    }){
                                    Icon(
                                        painterResource(
                                            id = R.drawable.filter
                                        ),
                                        contentDescription = "user",
                                        tint = colorResource(id = R.color.white),
                                        modifier = Modifier
                                            .size(30.dp)
                                            .align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    ImagePager()
                    Spacer(modifier = Modifier.height(16.dp))
                    GetCategories(onCategorySelected = {
                        homeViewModel.getProductsByCategories(it)
                        id .value= it.toInt()
                    })
                    Spacer(modifier = Modifier.height(16.dp))
                    GetProduct(product)
                }
            }
            LottieAnimationLoading(isLoading)

        }

    }
    @Composable
    fun ImagePager() {
        val pagerState = rememberPagerState()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                count = 4,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(colorResource(id = R.color.scoung))
                ) {
                    Box(modifier = Modifier
                        .height(170.dp)
                        .fillMaxWidth()
                        .clip(RectangleShape)
                        .background(
                            colorResource(id = R.color.scoung)
                        )
                    ) {
                        Row (horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically){
                            Column (modifier = Modifier
                                .weight(1.2f)
                                .padding(start = 24.dp)
                                .align(Alignment.CenterVertically)){
                                Text(
                                    text = stringResource(id = R.string.new_collection),
                                    style = TextStyle(
                                        fontSize = 17.sp,
                                        fontFamily = poppins,
                                        fontWeight = FontWeight.SemiBold,
                                        color = colorResource(id = R.color.brown)
                                    )
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = stringResource(id = R.string.discount_50),
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontFamily = poppins,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.Gray
                                    )
                                )
                            }

                            Image(painterResource(id = R.drawable.image), contentDescription = null,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize())
                        }


                    }
                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                activeColor = colorResource(id = R.color.app_color),
                indicatorWidth = 10.dp,

            )

        }
    }
    @Composable
    fun GetCategories(  onCategorySelected: (String) -> Unit){
        val categories = homeViewModel.categoriesResponse.collectAsState().value
        Column (modifier = Modifier.fillMaxWidth()){
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.category),
                    modifier = Modifier.align(Alignment.TopStart),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.brown)
                    ))
                Text(text = stringResource(id = R.string.view_all),
                        modifier = Modifier.align(Alignment.TopEnd),
                        style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.brown))
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(modifier = Modifier){
                items(categories?.data?.categories?: emptyList()){
                    CategoryCard(it, onCategorySelected)
                }
            }
        }
    }

    @Composable
    fun CategoryCard(category : Category, onCategorySelected: (String) -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ){
        Card(
            shape = CircleShape,
            border = if (id.value == category.id) BorderStroke(1.dp, colorResource(id = R.color.app_color)) else   BorderStroke(0.dp, colorResource(id = R.color.scoung)) ,
            modifier = Modifier
                .size(80.dp)

        ) {
            Card(
                shape = CircleShape,
                border = if (id.value == category.id) BorderStroke(3.dp, colorResource(id = R.color.scoung))
                else   BorderStroke(3.dp, Color.LightGray),
                modifier = Modifier
                    .size(80.dp)
                    .clickable {
                        onCategorySelected(category.id.toString())
                    }
            ){
                AsyncImage(
                    model = category.image,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.logo_shop),
                    error = painterResource(id = R.drawable.logo_shop)
                )
            }
            }
            Text(
                text = category.name?:"",
                modifier = Modifier.padding(top = 8.dp)
            )
        }

    }

    @Composable
    fun GetProduct(products: List<Products>){

        Column (modifier = Modifier.fillMaxWidth()){
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.hot_sale),
                    modifier = Modifier.align(Alignment.TopStart),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.brown)
                    ))
                Text(text = stringResource(id = R.string.view_all),
                    modifier = Modifier.align(Alignment.TopEnd),
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.brown))
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(products) { product ->
                    ProductCard(product = product)
                }
            }
        }

    }
    @Composable
    fun ProductCard(product: Products) {
        val context = LocalContext.current
        val rating = Random.nextInt(3, 5)
        var isSelected by remember { mutableStateOf(product.inFavorites) }
        Card(
            modifier = Modifier
                .width(200.dp)
                .padding(top = 8.dp, start = 4.dp, end = 4.dp, bottom = 8.dp)
                .wrapContentHeight()
                .clickable {
                    detailsProductViewModel.getProductById(product.id.toString())
                     navigator.push(DetailsProductScreen(product.id.toString()))
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
                    Card (colors = CardDefaults.cardColors(colorResource(id = R.color.white)),
                          elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),){
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

                    if (product.discount != null && product.discount?:0 > 0) {
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .width(40.dp)
                                .height(20.dp)
                                .clip(CircleShape)
                                .background(Color.Red)
                                .align(Alignment.TopStart)
                        ) {
                            Text(
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

                            Text(
                                text = "£${product.oldPrice}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Start,
                                color = colorResource(id = R.color.brown),
                                fontFamily = poppins,
                                modifier = Modifier.fillMaxWidth()
                            )
                        } else {

                            Text(
                                text = "£${product.oldPrice}",
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = Color.Gray,
                                textDecoration = TextDecoration.LineThrough,
                                fontFamily = poppins,
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
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
                            id = if (isSelected==true) R.drawable.heart_se else R.drawable.heart_un
                        ),
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(24.dp)
                            .clickable {
                                favoriteViewModel.addOrDeleteFavorite(product.id.toString()) {
                                    Toast
                                        .makeText(context, it, Toast.LENGTH_SHORT)
                                        .show()
                                    homeViewModel.homeResponse
                                }
                                isSelected = !isSelected!!

                            },
                        tint = if (isSelected== true) colorResource(id = R.color.app_color) else Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
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
                    Text(
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
                        Text(
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