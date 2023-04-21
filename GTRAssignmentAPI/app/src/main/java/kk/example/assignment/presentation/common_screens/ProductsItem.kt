package kk.example.jetpackcomposesturacturalapp.presentation.common_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kk.example.assignment.R
import kk.example.jetpackcomposesturacturalapp.data.remote.model.ModelProducts

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductsItem(
    modelProducts: ModelProducts?,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier.padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .size(100.dp)
                .padding(20.dp)
            ) {
                Image(
                    modifier = Modifier.clip(CircleShape),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                )
            }

            Spacer(modifier = Modifier.padding(5.dp))

            Column() {
                Text(
                    text ="${modelProducts?.Name}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "price : $${modelProducts?.Price}",
                    color = Color.Black,
                    fontWeight = FontWeight.Thin
                )
                Text(
                    text = "Cost Price : $${modelProducts?.CostPrice}",
                    color = Color.Black,
                    fontWeight = FontWeight.Thin
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductsItem2(
    modelProducts: ModelProducts,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier.padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement =Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Image(
                    modifier = Modifier.clip(RoundedCornerShape(topStart = 55.dp)),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                )


                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = modelProducts.Name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "price : $${modelProducts.Price}",
                    color = Color.Black,
                    fontWeight = FontWeight.Thin
                )

                Text(
                    text = "Cost Price : $${modelProducts.CostPrice}",
                    color = Color.Black,
                    fontWeight = FontWeight.Thin
                )
            }





        }

    }
}

