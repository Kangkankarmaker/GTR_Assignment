package kk.example.gtrassignment02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kk.example.jetpackcomposesturacturalapp.presentation.navigation.RootNavigationGraph
import kk.example.jetpackcomposesturacturalapp.presentation.utils.ConnectionLiveData
import kk.example.jetpackcomposesturacturalapp.presentation.viewModel.SplashViewModel
import javax.inject.Inject

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    lateinit var connectionLiveData: ConnectionLiveData



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition{
            !splashViewModel.isLoading.value
        }

        // network state check
        connectionLiveData= ConnectionLiveData(this)


        setContent {


            AppTheme(useDarkTheme=false) {
               val screen by splashViewModel.startDestination

                //set up startDestination nav
                val navController= rememberNavController()
                RootNavigationGraph(navController=navController, startDestination = screen)

                // network state check
                val isNetworkAvailable=connectionLiveData.observeAsState(true).value

                if (!isNetworkAvailable)
                    Snackbar(modifier = Modifier.padding(5.dp),
                        backgroundColor = MaterialTheme.colors.error) {
                        Row(verticalAlignment = Alignment.CenterVertically ) {
                            Icon(modifier = Modifier.padding(10.dp), painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")
                            Text(text = "No Internet Connected" )
                        }

                    }

            }
        }

    }
}


