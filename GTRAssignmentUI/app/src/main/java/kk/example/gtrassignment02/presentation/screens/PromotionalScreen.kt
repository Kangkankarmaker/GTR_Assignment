package kk.example.jetpackcomposesturacturalapp.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kk.example.gtrassignment02.R

@Composable
fun PromotionalScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        AppBar2()
        TotalItem()

        ProductsGrid()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar2() {
    val context = LocalContext.current
    CenterAlignedTopAppBar(
        modifier = Modifier,
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.green)),
        title = {
            Text(text = "Fruits and vegetables", color = Color.White)
        },
        actions = {


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
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Menu icon",
                    tint = Color.White
                )
            }
        },


        )
}

@Composable
fun TotalItem() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray.copy(.1f))
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "25 Products found")

        Icon(painter = painterResource(id = R.drawable.baseline_sort_24), contentDescription = null)
    }

}


@Composable
fun ProductsGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 50.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(15) {

            LoadData2(
                imagePainter = painterResource(id = R.drawable.apple),
                title = "Green Apple",
                price = "20",
                cutPrice = "15"
            )


        }
    }

}

@Composable
fun LoadData2(
    title: String = "",
    price: String = "",
    cutPrice: String = "",
    imagePainter: Painter
) {
    androidx.compose.material.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
    ) {
        Column {
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(10.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(19.dp),
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {

                Image(
                    modifier=Modifier.size(100.dp),
                    painter = imagePainter,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = title)
                Row() {
                    Text(
                        text = "৳ $price",
                        color = Color.Gray.copy(.5f),
                        textDecoration = TextDecoration.LineThrough
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "৳ $cutPrice",
                        color = colorResource(id = R.color.green),

                        )
                }



            }

            Divider(color = colorResource(id = R.color.green), thickness = .5.dp)

            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Add to cart",
                    color = colorResource(id = R.color.green)
                )
            }

        }



    }
}



