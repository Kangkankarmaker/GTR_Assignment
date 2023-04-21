package kk.example.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import kk.example.assignment.ui.theme.AssignmentTheme
import kk.example.jetpackcomposesturacturalapp.presentation.screens.ProductScreen
import kk.example.jetpackcomposesturacturalapp.presentation.utils.ConnectionLiveData


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var connectionLiveData: ConnectionLiveData


    override fun onCreate(savedInstanceState: Bundle?) {

        // network state check
        connectionLiveData= ConnectionLiveData(this)


        super.onCreate(savedInstanceState)
        setContent {
            AssignmentTheme {

                //app ui screen
                ProductScreen()


                // network state check
                val isNetworkAvailable=connectionLiveData.observeAsState(true).value

                if (!isNetworkAvailable)
                    Snackbar(modifier = Modifier.padding(5.dp),
                        backgroundColor = MaterialTheme.colors.error) {
                        Row(verticalAlignment = Alignment.CenterVertically ) {
                            Icon(modifier = Modifier.padding(10.dp), painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "")
                            Text(text = "No Internet Connected" )
                        }

                    }
            }
        }
    }
}

