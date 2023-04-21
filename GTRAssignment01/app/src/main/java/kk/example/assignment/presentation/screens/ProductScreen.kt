package kk.example.jetpackcomposesturacturalapp.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kk.example.jetpackcomposesturacturalapp.presentation.common_screens.*
import kk.example.jetpackcomposesturacturalapp.data.remote.model.ModelProducts
import kk.example.jetpackcomposesturacturalapp.presentation.utils.ConnectionLiveData
import kk.example.jetpackcomposesturacturalapp.presentation.utils.Resource
import kk.example.jetpackcomposesturacturalapp.presentation.viewModel.ProductScreenViewModel
import kotlin.concurrent.thread

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    viewModel: ProductScreenViewModel = hiltViewModel()
    ) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    var loadingVisible by remember { mutableStateOf(false) }
    val connectionLiveData = ConnectionLiveData(context)
    val refreshing by remember { mutableStateOf(false) }
    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState.collectAsState()
    val scrollBehavior= TopAppBarDefaults.pinnedScrollBehavior()


    val isNetworkAvailable=connectionLiveData.observeAsState(false).value
    if (isNetworkAvailable) {
        viewModel.preformApiGetProduct()
    }

    LaunchedEffect(Unit){
        viewModel.productData.observe(lifecycleOwner) {
            it.getContentIfNotHandled()?.let {event->
                when(event){
                    is Resource.Success->loadingVisible=false
                    is Resource.Error-> {
                        loadingVisible=false
                        //context.messageToast("Error while loading")
                    }
                    is Resource.Loading-> {
                        loadingVisible=true
                    }

                }
            }
        }
    }



    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MainAppBar(
                scrollBehavior=scrollBehavior,
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(newValue = it)
                    Log.d("onTextChange", it)
                },
                onCloseClicked = {
                    viewModel.updateSearchTextState(newValue = "")
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    Log.d("clicked", it)
                },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                },
                mainViewModel=viewModel

            )
        },
        content = {
            Content(refreshing, loadingVisible, viewModel)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    scrollBehavior:TopAppBarScrollBehavior,
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    mainViewModel: ProductScreenViewModel
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultAppBar(
                scrollBehavior=scrollBehavior,
                onSearchClicked = onSearchTriggered
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked,
                mainViewModel=mainViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(
    onSearchClicked: () -> Unit,
    scrollBehavior:TopAppBarScrollBehavior
) {
    SmallTopAppBar(
        modifier = Modifier
            .border(
                border =  BorderStroke(1.dp, Color.Gray),
                shape = CutCornerShape(bottomEnd = 30.dp)
            ),
        title = {
            Text(text = "Products")
        },
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    mainViewModel: ProductScreenViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 15.dp, end = 15.dp),
                value = text,
                onValueChange = {
                    onTextChange(it)
                },
                textStyle = MaterialTheme.typography.titleSmall,
                singleLine = true,
                leadingIcon = {
                    IconButton(modifier = Modifier
                        .alpha(DefaultAlpha),
                        onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )

                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (text.isNotEmpty()) {
                                onTextChange("")
                            } else {
                                onCloseClicked()
                            }
                        }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Search",
                            tint = Color.White
                        )

                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(text)
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    textColor =  Color.Black
                ),
                shape = RoundedCornerShape(
                    topStart = 10.dp, topEnd = 10.dp
                )
            )

            val persons by mainViewModel.products.collectAsState()
            val isSearching by mainViewModel.isSearching.collectAsState()

            Spacer(modifier = Modifier.height(10.dp))
            if(isSearching) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                LazyColumn(modifier = Modifier.padding(bottom = 60.dp),) {
                    items(
                        items = persons,
                        key = { it.Id }
                    ) { modelProduct: ModelProducts ->
                        LoadData(modelProduct)
                    }
                }


            }
        }
    }

}

@Composable
private fun Content(
    refreshing: Boolean,
    loadingVisible: Boolean,
    viewModel: ProductScreenViewModel
) {
    var refreshing1 = refreshing




    Column {


        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = refreshing1),
            onRefresh = { refreshing1 = true },
        ) {
            LoadLazyColumn(loadingVisible, viewModel)
            if (refreshing1) {
                thread {
                    Thread.sleep(1000)
                    refreshing1 = false
                }

            }
        }
    }
}

@Composable
private fun LoadLazyColumn(
    loadingVisible: Boolean,
    viewModel: ProductScreenViewModel
) {
    if (loadingVisible) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    val lazyListState= rememberLazyListState()

    val products by viewModel.products.collectAsState()
    LazyColumn(
        modifier = Modifier.padding(bottom = 60.dp),
        state = lazyListState
    ) {

        if (products.isEmpty()) {
            item {
                repeat(10) {
                    AnimatedShimmer(loadingVisible)
                }
            }
        }

        items(
            items = products,
            key = { it.Id }
        ) { modelProduct: ModelProducts ->
            LoadData(modelProduct)
        }
    }
}

@Composable
fun LoadData(modelProduct: ModelProducts) {
    val context = LocalContext.current
    
    BoxWithConstraints {

        Log.d("this.maxHeight", this.maxWidth.toString())
        if (this.maxWidth < 550.dp) {
            ProductsItem(
                modelProducts = modelProduct,
                onClick = {
                    Toast.makeText(context,modelProduct.Id,Toast.LENGTH_SHORT).show()
                }
            )
        }else{
            ProductsItem2(
                modelProducts = modelProduct,
                onClick = {
                    Toast.makeText(context,modelProduct.Id,Toast.LENGTH_SHORT).show()
                }
            )
        }

    }

}


