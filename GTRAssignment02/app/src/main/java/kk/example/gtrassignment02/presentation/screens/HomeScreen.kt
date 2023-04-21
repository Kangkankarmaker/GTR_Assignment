package kk.example.jetpackcomposesturacturalapp.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kk.example.gtrassignment02.R
import kk.example.jetpackcomposesturacturalapp.presentation.common_screens.ProductsItem
import kk.example.jetpackcomposesturacturalapp.ui.theme.*
import java.util.*


@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        AppBar()
        Promotions()
        CategorySection()

        Category()

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    val context = LocalContext.current
    CenterAlignedTopAppBar(
        modifier = Modifier,
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.green)),
        title = {
            Image(
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo"
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Shopping Cart ",
                    tint = Color.White
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(context, "Hello There", Toast.LENGTH_LONG).show()
            }) {
                Icon(
                    imageVector = Icons.Default.Menu, contentDescription = "Menu icon",tint = Color.White
                )
            }
        },


        )
}

@Composable
fun Promotions() {
    LazyRow(
        Modifier
            .height(160.dp)
            .padding(top = 15.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            PromotionItem(
                imagePainter = painterResource(id = R.drawable.promotion),
                title = "Grocery",
                subtitle = "Start @",
                header = "$9",
                backgroundColor = MaterialTheme.colorScheme.primary
            )
            PromotionItem(
                imagePainter = painterResource(id = R.drawable.promotion),
                title = "Jet ",
                subtitle = "Discount",
                header = "20%",
                backgroundColor = MaterialTheme.colorScheme.inversePrimary
            )
        }
    }
}

@Composable
fun PromotionItem(
    title: String = "",
    subtitle: String = "",
    header: String = "",
    backgroundColor: Color = Color.Transparent,
    imagePainter: Painter
) {
    Surface(
        Modifier
            .width(300.dp)
            .padding(start = 10.dp),
        shape = RoundedCornerShape(8.dp),
        color = backgroundColor,
        //elevation = 0.dp
    ) {
        Row {
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = title, fontSize = 14.sp, color = Color.White)
                Text(
                    text = subtitle,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = header,
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Image(
                painter = imagePainter,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                alignment = Alignment.CenterEnd,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun CategorySection() {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Category", style = MaterialTheme.typography.headlineMedium)
            TextButton(onClick = {}) {
                Text(text = "More", color = MaterialTheme.colorScheme.primary)
            }
        }

        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryButton(
                text = "All",
                icon = painterResource(id = R.drawable.market),
                backgroundColor = Color(0xffFEF4E7)
            )
            CategoryButton(
                text = "Fruits",
                icon = painterResource(id = R.drawable.fruits),
                backgroundColor = Color(0xFFF3FBF3)
            )
            CategoryButton(
                text = "Vegetables",
                icon = painterResource(id = R.drawable.vegetables),
                backgroundColor = Color(0xFFE7FEEB)
            )
            CategoryButton(
                text = "Fish",
                icon = painterResource(id = R.drawable.fish),
                backgroundColor = Color(0xffF6E6E9)
            )
        }
    }
}

@Composable
fun CategoryButton(
    text: String = "", icon: Painter, backgroundColor: Color
) {
    Column(
        Modifier
            .width(72.dp)
            .clickable { }) {
        Box(
            Modifier
                .size(72.dp)
                .background(
                    color = backgroundColor, shape = RoundedCornerShape(12.dp)
                )
                .padding(18.dp)
        ) {
            Image(painter = icon, contentDescription = "", modifier = Modifier.fillMaxSize())
        }
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
    }
}

@Composable
fun Category() {
    LoadLazyColumn()
}

@Composable
private fun LoadLazyColumn(

) {

    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .height(600.dp)
            .padding(bottom = 60.dp)
            .padding(top = 30.dp), state = lazyListState
    ) {

        item {
            LoadData(
                imagePainter = painterResource(id = R.drawable.ic_veg),
                title = "Fruits and vegetables",
                subtitle = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
            )

            LoadData(
                imagePainter = painterResource(id = R.drawable.ic_cheese),
                title = "Grocery",
                subtitle = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
            )

            LoadData(
                imagePainter = painterResource(id = R.drawable.ic_meat),
                title = "Household Needs",
                subtitle = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
            )

            LoadData(
                imagePainter = painterResource(id = R.drawable.ic_orange),
                title = "Mans and women's ware",
                subtitle = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
            )
        }
    }
}

@Composable
fun LoadData(
    title: String = "",
    subtitle: String = "",
    imagePainter: Painter
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(2.dp),
        elevation = 5.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp)) {
                Image(
                    modifier = Modifier
                        .size(150.dp),
                    painter = imagePainter,
                    contentDescription = null,
                )
            }

            Spacer(modifier = Modifier.padding(horizontal = 15.dp))

            Column(modifier = Modifier.weight(5f)) {
                Text(
                    text = title,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )

                Text(
                    text = subtitle,
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontSize = MaterialTheme.typography.labelLarge.fontSize
                )

            }

            Spacer(modifier = Modifier.padding(horizontal = 15.dp))

            Box(modifier = Modifier.weight(1f)) {
                Image(
                    modifier = Modifier.clip(CircleShape),
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = null,
                )
            }

        }

    }
}

@Preview
@Composable
fun LoadDataPreview() {

    LoadData(
        imagePainter = painterResource(id = R.drawable.promotion),
        title = "Fruits and vegetables",
        subtitle = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
    )

}











